package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.service.DepartmentService;
import com.employee.demoproject.service.LoginDetailsService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Autowired
    private DataRetrieve dataRetrieve;

    static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);

    @Override
    public LoginDetails createEmployee(EmployeeDTO employeeDTO) throws DataServiceException{
        try{
            logger.info("creating employee in business layer.");
            Employee employee = new Employee();
            employee.setName(employeeDTO.getEmp_name());
            employee.setBirthday(employeeDTO.getBirthday());
            employee.setGender(employeeDTO.getGender());
            employee.setMobile(employeeDTO.getMobile());
            employee.setEmail(employeeDTO.getEmail());
            employee.setAddress(employeeDTO.getAddress());

            Integer departmentId = employeeDTO.getDepartmentId();
            Department department = departmentService.getDepartmentById(departmentId);
            employee.setDepartment(department);
            employee.setStatus("Pending");

            sessionFactory.getCurrentSession().persist(employee);

            LoginDetails loginDetails = loginDetailsService.createLogin(employee);

            System.out.println("Employee created...!!!");

            return loginDetails;
        }catch (Exception e){
            logger.error("Error in creating employee in business layer. "+e);
            throw new DataServiceException("Exception in creating employee in business layer. ",e);
        }
    }

    @Override
    public Employee updateEmployee(int empId, EmployeeDTO employeeDTO) throws DataServiceException{

        try{
            logger.info("Employee updating..!");
            Employee updateEmployee = sessionFactory.getCurrentSession().get(Employee.class,empId);
//            updateEmployee.setName(employeeDTO.getEmp_name());
            updateEmployee.setMobile(employeeDTO.getMobile());
//            updateEmployee.setEmail(employeeDTO.getEmail());
            updateEmployee.setAddress(employeeDTO.getAddress());

            Department department = departmentService.getDepartmentById(employeeDTO.getDepartmentId());
            updateEmployee.setDepartment(department);

//            loginDetailsService.updateUserName(empId,employeeDTO);

            sessionFactory.getCurrentSession().saveOrUpdate(updateEmployee);

            return updateEmployee;
        }catch (Exception e){
            logger.error("Error in updating employee in business  layer");
            throw new DataServiceException("Exception in update employee in business layer",e);
        }

    }

    @Override
    public List<Employee> getAllEmployee() throws DataServiceException{
        try{
            logger.info("Getting all employees in business");
            String query = "From Employee e ORDER BY e.id DESC";
            List<Employee> empList = dataRetrieve.processList(query,Employee.class);
            return empList;
        }catch (DataAccessException e){
            logger.error("Error in getting all employees in business layer. "+e);
            throw new DataServiceException("Exception in getting all employees in business layer. ",e);
        }

    }

    @Override
    public Employee getEmployeeById(int empId) throws DataServiceException{
        try {
            String q = "from Employee e\n" +
                       "where e.id =:id";
            Employee employee = dataRetrieve.getObjectById(q,empId,Employee.class);
            return employee;
        } catch (DataAccessException ex) {
            throw new DataAccessException("Error fetching employee data by ID: " + empId, ex);
        }
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForRoleAssign(int deptId) throws DataServiceException{
        try{
            String query = "FROM Employee e\n" +
                    "LEFT JOIN EmpRoleSalary ers \n" +
                    "ON e.id = ers.employee_role_salary.id\n" +
                    "WHERE e.status = 'activated'\n" +
                    "AND e.department.id = :id\n" +
                    "AND ers.employee_role_salary.id IS NULL";
            List<Employee> employeeList = dataRetrieve.getListById(query,deptId,Employee.class);
            return employeeList;
        }catch (DataAccessException e){
            logger.error("Error in getting employee for role assign. "+e);
            throw new DataServiceException("Exception in getting employee for role assign.",e);
        }
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForPayroll(int deptId) throws DataServiceException{
        try{
            logger.info("getting employee for payroll");
            String query = "FROM Employee e \n" +
                    "LEFT JOIN EmpRoleSalary ers \n" +
                    "ON e.id = ers.employee_role_salary.id \n" +
                    "left join Payroll p \n" +
                    "on p.empRoleSalary_payroll.id = ers.id \n" +
                    "WHERE e.status = 'activated'\n" +
                    "and e.department.id = :id \n" +
                    "and ers.employee_role_salary.id is not null \n" +
                    "AND p.empRoleSalary_payroll.id IS NULL";
            List<Employee> employeeList = dataRetrieve.getListById(query,deptId,Employee.class);
            return employeeList;
        }catch (DataAccessException e){
            logger.error("Error in getting employee for payroll. ");
            throw new DataServiceException("Exception in getting employee for payroll. ",e);
        }
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId) throws DataServiceException{
        try{
            logger.info("getting employee for leave assign. ");
            String query = "select distinct e \n" +
                    "from Employee e \n" +
                    "left join EmpRoleSalary ers \n" +
                    "on e.id = ers.employee_role_salary.id \n" +
                    "left join EmployeeHasLeave ehl \n" +
                    "on e.id = ehl.employee_has_leave.id \n" +
                    "where e.department.id = :id \n" +
                    "and e.status = 'activated' \n" +
                    "and ers.employee_role_salary.id is not null \n" +
                    "and ehl.employee_has_leave.id is null";
            List<Employee> employeeList = dataRetrieve.getListById(query,deptId,Employee.class);
            return employeeList;
        }catch (DataAccessException e){
            logger.error("Error in getting employee for leave assign. "+e);
            throw new DataServiceException("Exception in getting employee for Leave assign. ",e);
        }
    }

    @Override
    public Long getTotalEmployeeCount() throws DataServiceException{
        try {
            logger.info("Getting employees count in business layer");
            String query = "select count(e) from Employee e";
            return dataRetrieve.getCount(query);
        }catch (DataAccessException e){
            logger.error("Error in getting employees count in business layer");
            throw new DataServiceException("Exception in getting employee count in business layer",e);
        }
    }

    @Override
    public void deleteEmployee(int empId) throws DataServiceException{
        try{
            logger.info("Deleting employee...");
            Session session = sessionFactory.getCurrentSession();
            Employee employee = session.get(Employee.class,empId);
            session.remove(employee);
            System.out.println("Successfully deleted");
        }catch (Exception e){
            logger.error("Error in deleting employee in business layer. "+e);
            throw new DataServiceException("Exception in deleting employee in business layer. ",e);
        }

    }

    @Override
    public LeaveAssignDTO getEmployeeDetailCard(int empId) throws DataServiceException{
        try{
            logger.info("Getting employee details card...");
            String query = "select distinct e.id, e.name, d.name as dept, ds.role, ld.activated_on\n" +
                    "from Employee e \n" +
                    "join Department d \n" +
                    "on e.department.id = d.id\n" +
                    "join LoginDetails ld \n" +
                    "on e.id = ld.employee_login.id \n" +
                    "join EmpRoleSalary ers \n" +
                    "on e.id = ers.employee_role_salary.id \n" +
                    "join Designation ds \n" +
                    "on ers.designation.id = ds.id\n" +
                    "join EmployeeHasLeave ehl \n" +
                    "on e.id = ehl.employee_has_leave.id where ld.employee_login.id = :id";
            LeaveAssignDTO leaveAssignDTO = dataRetrieve.getObjectById(query,empId,LeaveAssignDTO.class);
            return leaveAssignDTO;
        }catch (DataAccessException e){
            logger.error("Error in getting employee card in business layer. "+e);
            throw new DataServiceException("Exception in getting employee card in business layer",e);
        }
    }
}
