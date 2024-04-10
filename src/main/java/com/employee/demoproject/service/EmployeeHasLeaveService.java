package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface EmployeeHasLeaveService {
    void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    void updateLeaveForEmployee(int empId, EmployeeHasLeaveDTO employeeHasLeaveDTO);
    List<EmployeeHasLeaveDTO> getAllEmployeeLeaves();
    List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId);
}
