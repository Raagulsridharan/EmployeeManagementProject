package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface EmployeeHasLeaveService {
    void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList);
    List<LeaveAssignDTO> getAllEmployeeLeaves();
    List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId);

    Long totalEmployeeHasLeave() throws BusinessServiceException;
    List<LeaveAssignDTO> filterEmployeeHasLeave(FilterOption filterOption) throws BusinessServiceException;
}
