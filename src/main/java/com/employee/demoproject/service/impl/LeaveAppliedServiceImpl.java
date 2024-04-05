package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LeaveAppliedDAO;
import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.service.LeaveAppliedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeaveAppliedServiceImpl implements LeaveAppliedService {

    @Autowired
    private LeaveAppliedDAO leaveAppliedDAO;

    @Override
    public Long getApprovedLeaveCount() {
        return leaveAppliedDAO.getApprovedLeaveCount();
    }

    @Override
    public Long getRejectedLeaveCount() {
        return leaveAppliedDAO.getRejectedLeaveCount();
    }

    @Override
    public Long getRequestedLeaveCount() {
        return leaveAppliedDAO.getRequestedLeaveCount();
    }

    @Override
    public List<LeaveApplied> getAllApprovedLeaves() {
        return leaveAppliedDAO.getAllApprovedLeaves();
    }

    @Override
    public List<LeaveApplied> getAllRejectedLeaves() {
        return leaveAppliedDAO.getAllRejectedLeaves();
    }

    @Override
    public List<LeaveApplied> getAllRequestedLeaves() {
        return leaveAppliedDAO.getAllRequestedLeaves();
    }

    @Override
    public List<LeaveApplied> getLeaveHistoryBYEmployee(int empId) {
        return leaveAppliedDAO.getLeaveHistoryBYEmployee(empId);
    }

    @Override
    public String empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO) {
        return leaveAppliedDAO.empApplyingLeave(empId,leaveAppliedDTO);
    }

    @Override
    public void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO) {
        leaveAppliedDAO.updateLeaveStatus(leaveAppliedDTO);
    }
}
