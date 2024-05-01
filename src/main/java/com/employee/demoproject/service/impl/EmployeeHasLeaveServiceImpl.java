package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.EmployeeHasLeaveDAO;
import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.EmployeeHasLeaveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeHasLeaveServiceImpl implements EmployeeHasLeaveService {

    @Autowired
    private EmployeeHasLeaveDAO employeeHasLeaveDAO;

    static Logger logger = Logger.getLogger(EmployeeHasLeaveServiceImpl.class);

    @Override
    public void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO) {
        employeeHasLeaveDAO.assignLeaveForEmployee(empId,employeeHasLeaveDTO);
    }

    @Override
    public void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList) {
        employeeHasLeaveDAO.updateLeaveForEmployee(empId,employeeHasLeaveDTOList);
    }

    @Override
    public List<LeaveAssignDTO> getAllEmployeeLeaves() {
        return employeeHasLeaveDAO.getAllEmployeesLeaves();
    }

    @Override
    public List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId) {
        return employeeHasLeaveDAO.getEmployeeLeave(empId);
    }

    @Override
    public Long totalEmployeeHasLeave() throws BusinessServiceException {
        try {
            logger.info("Entering the service layer for filtering the employee has leave.");
            return employeeHasLeaveDAO.totalEmployeeHasLeave();
        }catch (DataServiceException e){
            logger.error("Error in Service layer for filtering the employee has leave. "+e);
            throw new BusinessServiceException("Exception in Service layer for filtering the employee has leave");
        }
    }

    @Override
    public List<LeaveAssignDTO> filterEmployeeHasLeave(FilterOption filterOption) throws BusinessServiceException {
        try {
            logger.info("Entering to filtering the  employee has leave in service layer. ");
            return employeeHasLeaveDAO.filterEmployeeHasLeave(filterOption);
        }catch (DataServiceException e){
            logger.error("Error in filtering the employee has leave in service layer. "+e);
            throw new BusinessServiceException("Exception in filtering the employee has leave in service layer.",e);
        }
    }
}
