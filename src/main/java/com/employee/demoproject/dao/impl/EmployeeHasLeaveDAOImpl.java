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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeHasLeaveDAOImpl implements EmployeeHasLeaveDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LeavePolicyService leavePolicyService;

    @Override
    public void assignLeaveForEmployee(int id) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class,id);
        List<LeavePolicy> leavePolicies = leavePolicyService.getAllLeavePolicy();
        for(LeavePolicy leavePolicy : leavePolicies){
            EmployeeHasLeave employeeHasLeave = new EmployeeHasLeave();
            employeeHasLeave.setEmployee_has_leave(employee);
            employeeHasLeave.setLeavePolicy(leavePolicy);
            employeeHasLeave.setNo_of_days(10);
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            employeeHasLeave.setUpdated_on(sqlDate);
            sessionFactory.getCurrentSession().persist(employeeHasLeave);
        }
    }

    @Override
    public List<EmployeeHasLeave> getAllEmployeesLeaves() {
        List<EmployeeHasLeave> leavePolicies = sessionFactory.getCurrentSession().createQuery("SELECT lp FROM EmployeeHasLeave lp").getResultList();
        return leavePolicies;
    }
}
