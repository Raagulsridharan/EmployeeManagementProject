package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LeaveAppliedDAO;
import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.LeaveAppliedService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveAppliedServiceImpl implements LeaveAppliedService {

    @Autowired
    private LeaveAppliedDAO leaveAppliedDAO;

    static Logger logger = Logger.getLogger(LeaveAppliedServiceImpl.class);

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
    public LeaveAppliedDTO empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO) {
        try{
            logger.info("Leave applying in service");
            return mapToDTO(leaveAppliedDAO.empApplyingLeave(empId,leaveAppliedDTO));
        }catch (DataServiceException e){
            logger.error("Error in leave apply from service. "+e);
            throw new BusinessServiceException("Exception in service layer in leave apply.",e);
        }
    }

    @Override
    public void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO) {
        leaveAppliedDAO.updateLeaveStatus(leaveAppliedDTO);
    }

    @Override
    public Long getEmployeeLeaveHistoryCount(int empId) throws BusinessServiceException {
        try{
            logger.info("Getting employee history count by id in service layer");
            return leaveAppliedDAO.getEmployeeLeaveHistoryCount(empId);
        }catch (DataServiceException e) {
            logger.error("Error in service layer for getting employee history count. " + e);
            throw new BusinessServiceException("Exception in service layer for getting employee history count", e);
        }
    }

    @Override
    public List<LeaveAppliedDTO> filterLeaveApplied(Integer empId, FilterOption filterOption) throws BusinessServiceException {
        try {
            logger.info("Entering the method of filtering employee leave applied");
            List<LeaveApplied> leaveAppliedList = leaveAppliedDAO.filterLeaveApplied(empId,filterOption);
            return Optional.ofNullable(leaveAppliedList)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            logger.error("Error in service layer for filtering the employee leave applied. "+e);
            throw new BusinessServiceException("Exception in service layer for filtering the employee leave applied",e);
        }
    }

    private LeaveAppliedDTO mapToDTO(LeaveApplied leaveApplied){
        LeaveAppliedDTO leaveAppliedDTO = new LeaveAppliedDTO();

        leaveAppliedDTO.setId(leaveApplied.getId());
        leaveAppliedDTO.setEmpId(leaveApplied.getEmployee_leave_applied().getId());
        leaveAppliedDTO.setLeaveType(leaveApplied.getLeavePolicy().getId());
        leaveAppliedDTO.setNote(leaveApplied.getNote());
        leaveAppliedDTO.setFromDate(leaveApplied.getFrom_date());
        leaveAppliedDTO.setToDate(leaveApplied.getTo_date());
        leaveAppliedDTO.setStatus(leaveApplied.getStatus());
        leaveAppliedDTO.setNoOfDays(leaveApplied.getNo_of_days());
        leaveAppliedDTO.setSubmittedOn(leaveApplied.getSubmitted_on());

        return leaveAppliedDTO;
    }
}
