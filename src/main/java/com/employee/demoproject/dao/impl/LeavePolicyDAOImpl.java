package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.LeavePolicyDAO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.entity.LeavePolicy;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeavePolicyDAOImpl implements LeavePolicyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void creatLeavePolicy(LeavePolicy leavePolicy) {
        sessionFactory.getCurrentSession().persist(leavePolicy);
        System.out.println("Leave policy created...");
    }

    @Override
    public List<LeavePolicy> getAllLeavePolicy() {
        List<LeavePolicy> designationList = sessionFactory.getCurrentSession()
                .createQuery("select lp from LeavePolicy lp")
                .getResultList();
        return designationList;
    }

    @Override
    public LeavePolicy getLeavePolicyById(int id) {
        return sessionFactory.getCurrentSession().get(LeavePolicy.class,id);
    }

    @Override
    public LeavePolicy getLeavePolicyByName(String name) {
        Query<LeavePolicy> query = sessionFactory.getCurrentSession()
                .createQuery("select lp from LeavePolicy lp where lp.leave_types = :leaveName", LeavePolicy.class)
                .setParameter("leaveName", name);
        return query.uniqueResult();
    }
}
