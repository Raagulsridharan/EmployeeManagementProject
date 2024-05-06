package com.employee.demoproject.service;


import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface LeaveAppliedService {
    Long getApprovedLeaveCount();
    Long getRejectedLeaveCount();
    Long getRequestedLeaveCount();
    List<LeaveApplied> getAllApprovedLeaves();
    List<LeaveApplied> getAllRejectedLeaves();
    List<LeaveApplied> getAllRequestedLeaves();
    List<LeaveApplied> getLeaveHistoryBYEmployee(int empId);
    LeaveAppliedDTO empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO) throws BusinessServiceException;
    void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO);
    Long getEmployeeLeaveHistoryCount(int empId) throws BusinessServiceException;
    List<LeaveAppliedDTO> filterLeaveApplied(Integer empId, FilterOption filterOption) throws BusinessServiceException;
}
