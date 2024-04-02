package com.employee.demoproject.dao;

import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface LeavePolicyDAO {
    void creatLeavePolicy(LeavePolicy leavePolicy);
    List<LeavePolicy> getAllLeavePolicy();
    LeavePolicy getLeavePolicyById(int id);
    LeavePolicy getLeavePolicyByName(String name);
}
