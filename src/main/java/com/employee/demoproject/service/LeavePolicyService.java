package com.employee.demoproject.service;

import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface LeavePolicyService {
    void createLeavePolicy(LeavePolicy leavePolicy);
    List<LeavePolicy> getAllLeavePolicy();
    LeavePolicy getLeavePolicyById(int id);
    LeavePolicy getLeavePolicyByName(String name);
}
