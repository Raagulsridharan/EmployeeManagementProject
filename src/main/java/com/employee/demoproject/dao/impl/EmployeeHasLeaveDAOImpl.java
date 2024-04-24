package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeHasLeaveDAO;
import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
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

    @Override
    public void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class,empId);

        for(EmployeeHasLeaveDTO leavePolicy : employeeHasLeaveDTO){

            EmployeeHasLeave employeeHasLeave = new EmployeeHasLeave();
              employeeHasLeave.setEmployee_has_leave(employee);
            LeavePolicy leavePolicy1 = sessionFactory.getCurrentSession().get(LeavePolicy.class,leavePolicy.getLeaveId());
              employeeHasLeave.setLeavePolicy(leavePolicy1);
              employeeHasLeave.setNo_of_days(leavePolicy.getNoOfdays());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
              employeeHasLeave.setUpdated_on(sqlDate);
            sessionFactory.getCurrentSession().persist(employeeHasLeave);

            System.out.println("Successfully Leave assigned");
        }

    }

    @Override
    public void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList) {
        for(EmployeeHasLeaveDTO employeeHasLeaveDTO: employeeHasLeaveDTOList){
            EmployeeHasLeave employeeHasLeave = sessionFactory.getCurrentSession()
                    .createQuery("from EmployeeHasLeave ehl \n" +
                            "where ehl.employee_has_leave.id = :empId\n" +
                            "and ehl.leavePolicy.id = :leaveId",EmployeeHasLeave.class)
                    .setParameter("empId",empId)
                    .setParameter("leaveId",employeeHasLeaveDTO.getLeaveId())
                    .getSingleResult();
              employeeHasLeave.setNo_of_days(employeeHasLeaveDTO.getNoOfdays());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
              employeeHasLeave.setUpdated_on(sqlDate);
            sessionFactory.getCurrentSession().saveOrUpdate(employeeHasLeave);
        }
    }

    @Override
    public List<LeaveAssignDTO> getAllEmployeesLeaves() {
        List<LeaveAssignDTO> leavePolicies = sessionFactory.getCurrentSession()
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
                                                                     "on e.id = ehl.employee_has_leave.id",LeaveAssignDTO.class)
                                                             .getResultList();
        return leavePolicies;
    }

    @Override
    public List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId) {
        List<EmployeeHasLeaveDTO> leaveDTOS = sessionFactory.getCurrentSession()
                                                            .createQuery("SELECT lp.id, lp.leave_types, ehl.no_of_days, ehl.updated_on\n" +
                                                                    "FROM EmployeeHasLeave ehl \n" +
                                                                    "join LeavePolicy lp \n" +
                                                                    "on ehl.leavePolicy.id = lp.id \n" +
                                                                    "where ehl.employee_has_leave.id = :empId",EmployeeHasLeaveDTO.class)
                                                            .setParameter("empId",empId)
                                                            .getResultList();
        return leaveDTOS;
    }
}
