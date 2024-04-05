package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LeavePolicyDAO;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.LeavePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeavePolicyServiceImpl implements LeavePolicyService {

    @Autowired
    private LeavePolicyDAO leavePolicyDAO;

    @Override
    public void createLeavePolicy(LeavePolicy leavePolicy) {
        leavePolicyDAO.creatLeavePolicy(leavePolicy);
    }

    @Override
    public List<LeavePolicy> getAllLeavePolicy() {
        return leavePolicyDAO.getAllLeavePolicy();
    }

    @Override
    public LeavePolicy getLeavePolicyById(int id) {
        return leavePolicyDAO.getLeavePolicyById(id);
    }

    @Override
    public LeavePolicy getLeavePolicyByName(String name) {
        return leavePolicyDAO.getLeavePolicyByName(name);
    }

    @Override
    public Long getLeaveTypesCount() {
        return leavePolicyDAO.getLeaveTypesCount();
    }
}
