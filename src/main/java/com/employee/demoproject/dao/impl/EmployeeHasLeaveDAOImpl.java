package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeHasLeaveDAO;
import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.LeavePolicyService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeHasLeaveDAOImpl implements EmployeeHasLeaveDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LeavePolicyService leavePolicyService;

    @Override
    public void assignLeaveForEmployee(int id, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class,id);
        for(EmployeeHasLeaveDTO leavePolicy : employeeHasLeaveDTO){
            EmployeeHasLeave employeeHasLeave = new EmployeeHasLeave();
            employeeHasLeave.setEmployee_has_leave(employee);
            LeavePolicy leavePolicy1 = sessionFactory.getCurrentSession().get(LeavePolicy.class,leavePolicy.getLeaveId());
            employeeHasLeave.setLeavePolicy(leavePolicy1);
            employeeHasLeave.setNo_of_days(leavePolicy.getNoOfDays());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            employeeHasLeave.setUpdated_on(sqlDate);
            sessionFactory.getCurrentSession().persist(employeeHasLeave);
            System.out.println("Successfully Leave assigned");
        }
    }

    @Override
    public List<EmployeeHasLeaveDTO> getAllEmployeesLeaves() {
        List<EmployeeHasLeaveDTO> leavePolicies = sessionFactory.getCurrentSession()
                                                             .createQuery("select distinct e.id, e.name, d.name as dept, ds.role, ld.activated_on\n" +
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
                                                                     "on e.id = ehl.employee_has_leave.id",EmployeeHasLeaveDTO.class)
                                                             .getResultList();
        return leavePolicies;
    }
}
