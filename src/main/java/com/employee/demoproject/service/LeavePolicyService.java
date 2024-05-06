package com.employee.demoproject.service;

import com.employee.demoproject.dto.LeavePolicyDTO;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;

import java.util.List;

public interface LeavePolicyService {
    void createLeavePolicy(LeavePolicy leavePolicy);
    List<LeavePolicyDTO> getAllLeavePolicy();
    LeavePolicy getLeavePolicyById(int id);
    LeavePolicy getLeavePolicyByName(String name);
    Long getLeaveTypesCount();
    List<LeavePolicyDTO> getEmployeeLeave(Integer empId) throws BusinessServiceException;
}
