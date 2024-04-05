package com.employee.demoproject.service;


import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.LeaveApplied;

import java.util.List;

public interface LeaveAppliedService {
    Long getApprovedLeaveCount();
    Long getRejectedLeaveCount();
    Long getRequestedLeaveCount();
    List<LeaveApplied> getAllApprovedLeaves();
    List<LeaveApplied> getAllRejectedLeaves();
    List<LeaveApplied> getAllRequestedLeaves();
    List<LeaveApplied> getLeaveHistoryBYEmployee(int empId);
    String empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO);
    void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO);
}
