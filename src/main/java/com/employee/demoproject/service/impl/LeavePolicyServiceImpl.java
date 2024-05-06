package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LeavePolicyDAO;
import com.employee.demoproject.dto.LeavePolicyDTO;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.service.LeavePolicyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeavePolicyServiceImpl implements LeavePolicyService {

    @Autowired
    private LeavePolicyDAO leavePolicyDAO;

    static Logger logger = Logger.getLogger(LeavePolicyServiceImpl.class);

    @Override
    public void createLeavePolicy(LeavePolicy leavePolicy) {
        leavePolicyDAO.creatLeavePolicy(leavePolicy);
    }

    @Override
    public List<LeavePolicyDTO> getAllLeavePolicy() {
        List<LeavePolicy> leavePolicyList = leavePolicyDAO.getAllLeavePolicy();
        List<LeavePolicyDTO> leavePolicyDTOS = new ArrayList<>();
        for(LeavePolicy leavePolicy: leavePolicyList){
            leavePolicyDTOS.add(mapToDTO(leavePolicy));
        }
        return leavePolicyDTOS;
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

    @Override
    public List<LeavePolicyDTO> getEmployeeLeave(Integer empId) throws BusinessServiceException {
        try {
            logger.info("Entering to fetch employee leave type");
            List<LeavePolicy> leavePolicies = leavePolicyDAO.getEmployeeLeave(empId);
            return Optional.ofNullable(leavePolicies)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            logger.error("Error in fetch employee leave types. "+e);
            throw new BusinessServiceException("Exception in service to fetch employee leave",e);
        }
    }

    private LeavePolicyDTO mapToDTO(LeavePolicy leavePolicy){
        LeavePolicyDTO leavePolicyDTO = new LeavePolicyDTO();
        leavePolicyDTO.setId(leavePolicy.getId());
        leavePolicyDTO.setLeaveType(leavePolicy.getLeave_types());
        return leavePolicyDTO;
    }
}
