package com.employee.demoproject.dao;

import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.exceptions.DataServiceException;

import java.util.List;

public interface LeaveAppliedDAO {
    Long getApprovedLeaveCount();
    Long getRejectedLeaveCount();
    Long getRequestedLeaveCount();
    List<LeaveApplied> getAllApprovedLeaves();
    List<LeaveApplied> getAllRejectedLeaves();
    List<LeaveApplied> getAllRequestedLeaves();
    List<LeaveApplied> getLeaveHistoryBYEmployee(int empId);
    LeaveApplied empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO);
    void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO);
    Long getEmployeeLeaveHistoryCount(int empId) throws DataServiceException;
}
