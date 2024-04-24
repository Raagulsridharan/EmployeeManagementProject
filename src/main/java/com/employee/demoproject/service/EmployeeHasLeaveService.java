package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface EmployeeHasLeaveService {
    void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList);
    List<LeaveAssignDTO> getAllEmployeeLeaves();
    List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId);
}
