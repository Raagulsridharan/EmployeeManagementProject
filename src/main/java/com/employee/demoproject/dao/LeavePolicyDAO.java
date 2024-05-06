package com.employee.demoproject.dao;

import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.DataServiceException;

import java.util.List;

public interface LeavePolicyDAO {
    void creatLeavePolicy(LeavePolicy leavePolicy);
    List<LeavePolicy> getAllLeavePolicy();
    LeavePolicy getLeavePolicyById(int id);
    LeavePolicy getLeavePolicyByName(String name);
    Long getLeaveTypesCount();
    List<LeavePolicy> getEmployeeLeave(Integer empId) throws DataServiceException;
}
