package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.service.DepartmentService;
import com.employee.demoproject.service.LoginDetailsService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Override
    public void createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getEmp_name());
        employee.setGender(employeeDTO.getGender());
        employee.setMobile(employeeDTO.getMobile());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());

        String departmentName = employeeDTO.getDepartment();
        Department department = departmentService.getDeptByName(departmentName);
        employee.setDepartment(department);
        employee.setStatus("Pending");

        sessionFactory.getCurrentSession().persist(employee);

        loginDetailsService.createLogin(employee);

        System.out.println("Employee created...!!!");
    }

    @Override
    public void updateEmployee(int id, EmployeeDTO employeeDTO) {
        Employee updateEmployee = sessionFactory.getCurrentSession().get(Employee.class,id);
        updateEmployee.setName(employeeDTO.getEmp_name());
        updateEmployee.setMobile(employeeDTO.getMobile());
        updateEmployee.setEmail(employeeDTO.getEmail());
        updateEmployee.setAddress(employeeDTO.getAddress());

        String departmentName = employeeDTO.getDepartment();
        Department department = departmentService.getDeptByName(departmentName);
        updateEmployee.setDepartment(department);

        loginDetailsService.updateUserName(id,employeeDTO);

        sessionFactory.getCurrentSession().saveOrUpdate(updateEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(){
        Query<EmployeeDTO> query = sessionFactory.getCurrentSession()
                .createNativeQuery("select e.id,e.name as emp_name, e.gender, e.mobile, e.email, e.address, d.name as department \n" +
                "from employee e\n" +
                "left join department d\n" +
                "on e.dept_id = d.id;",EmployeeDTO.class);
        List<EmployeeDTO> empList = query.getResultList();
        return empList;
    }

    @Override
    public EmployeeDTO getEmployeeById(int id){
        Query<EmployeeDTO> query = sessionFactory.getCurrentSession()
                .createQuery("select e.id,e.name as emp_name, e.gender, e.mobile, e.email, e.address, d.name as department \n" +
                        "from Employee e\n" +
                        "left join Department d\n" +
                        "on e.department.id = d.id\n" +
                        "where e.id =:emp_id",EmployeeDTO.class)
                .setParameter("emp_id",id);
        return query.uniqueResult();
    }

    @Override
    public List<Employee> getAllEmployeeByDept(int deptId) {
        List<Employee> employeeList = sessionFactory.getCurrentSession()
                                                    .createQuery("SELECT e\n" +
                                                            "FROM Employee e\n" +
                                                            "LEFT JOIN EmpRoleSalary ers \n" +
                                                            "ON e.id = ers.employee_role_salary.id\n" +
                                                            "WHERE e.status = 'activated'\n" +
                                                            "AND e.department.id = :deptId\n" +
                                                            "AND ers.employee_role_salary.id IS NULL")
                                                    .setParameter("deptId",deptId)
                                                    .getResultList();
        return employeeList;
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForPayroll(int deptId) {
        List<Employee> employeeList = sessionFactory.getCurrentSession()
                                                    .createQuery("SELECT e \n" +
                                                            "FROM Employee e \n" +
                                                            "LEFT JOIN EmpRoleSalary ers \n" +
                                                            "ON e.id = ers.employee_role_salary.id \n" +
                                                            "left join Payroll p \n" +
                                                            "on p.empRoleSalary_payroll.id = ers.id \n" +
                                                            "WHERE e.status = 'activated'\n" +
                                                            "and e.department.id = :deptId \n" +
                                                            "and ers.employee_role_salary.id is not null \n" +
                                                            "AND p.empRoleSalary_payroll.id IS NULL")
                                                    .setParameter("deptId",deptId)
                                                    .getResultList();
        return employeeList;
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId) {
        List<Employee> employeeList = sessionFactory.getCurrentSession()
                                                    .createQuery("select distinct e \n" +
                                                            "from Employee e \n" +
                                                            "left join EmpRoleSalary ers \n" +
                                                            "on e.id = ers.employee_role_salary.id \n" +
                                                            "left join EmployeeHasLeave ehl \n" +
                                                            "on e.id = ehl.employee_has_leave.id \n" +
                                                            "where e.department.id = :deptId \n" +
                                                            "and e.status = 'activated' \n" +
                                                            "and ers.employee_role_salary.id is not null \n" +
                                                            "and ehl.employee_has_leave.id is null",Employee.class)
                                                    .setParameter("deptId",deptId)
                                                    .getResultList();
        return employeeList;
    }

    @Override
    public Long getTotalEmployeeCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Employee",Long.class);
        return query.uniqueResult();
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class,id);
        session.remove(employee);
        System.out.println("Successfully deleted");
    }
}
