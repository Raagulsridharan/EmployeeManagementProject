package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;

import java.util.List;

public interface EmployeeHasLeaveDAO {
    void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    void updateLeaveForEmployee(int empId, EmployeeHasLeaveDTO employeeHasLeaveDTO);
    List<EmployeeHasLeaveDTO> getAllEmployeesLeaves();
    List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId);
}
