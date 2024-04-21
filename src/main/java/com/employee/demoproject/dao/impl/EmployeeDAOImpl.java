package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.service.DepartmentService;
import com.employee.demoproject.service.LoginDetailsService;
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

    @Override
    public LoginDetails createEmployee(EmployeeDTO employeeDTO) {
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
    }

    @Override
    public Employee updateEmployee(int empId, EmployeeDTO employeeDTO){
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
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(){
        String query = "select e.id,e.name as emp_name, e.birthday, e.gender, e.mobile, e.email, e.address, d.id as departmentId,d.name as department \n" +
                "from Employee e\n" +
                "left join Department d\n" +
                "on e.department.id = d.id";
        List<EmployeeDTO> empList = dataRetrieve.processList(query,EmployeeDTO.class);
        return empList;
    }

    @Override
    public EmployeeDTO getEmployeeById(int empId) {
        try {
            String q = "select e.id,e.name as emp_name, e.birthday, e.gender, e.mobile, e.email, e.address, d.id as departmentId ,d.name as department \n" +
                    "from Employee e\n" +
                    "left join Department d\n" +
                    "on e.department.id = d.id\n" +
                    "where e.id =:id";
            EmployeeDTO employeeDTO = dataRetrieve.getObjectById(q,empId,EmployeeDTO.class);
            return employeeDTO;
        } catch (DataAccessException ex) {
            throw new DataAccessException("Error fetching employee data by ID: " + empId, ex);
        }
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForRoleAssign(int deptId) {
        String query = "FROM Employee e\n" +
                "LEFT JOIN EmpRoleSalary ers \n" +
                "ON e.id = ers.employee_role_salary.id\n" +
                "WHERE e.status = 'activated'\n" +
                "AND e.department.id = :id\n" +
                "AND ers.employee_role_salary.id IS NULL";
        List<Employee> employeeList = dataRetrieve.getListById(query,deptId,Employee.class);
        return employeeList;
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForPayroll(int deptId) {
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
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId) {
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
    }

    @Override
    public Long getTotalEmployeeCount() {
//        Query<Long> query1 = sessionFactory.getCurrentSession()
//                .createQuery("select count(e) from Employee e",Long.class);
        String query = "select count(e) from Employee e";
        return dataRetrieve.getCount(query);
    }

    @Override
    public void deleteEmployee(int empId) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class,empId);
        session.remove(employee);
        System.out.println("Successfully deleted");
    }
}
