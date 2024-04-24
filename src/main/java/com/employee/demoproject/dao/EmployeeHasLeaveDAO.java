package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;

import java.util.List;

public interface EmployeeHasLeaveDAO {
    void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList);
    List<LeaveAssignDTO> getAllEmployeesLeaves();
    List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId);
}
