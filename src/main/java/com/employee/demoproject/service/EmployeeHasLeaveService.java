package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface EmployeeHasLeaveService {
    void assignLeaveForEmployee(int id, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    List<EmployeeHasLeaveDTO> getAllEmployeeLeaves();
}
