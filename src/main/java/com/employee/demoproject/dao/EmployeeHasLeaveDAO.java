package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;

import java.util.List;

public interface EmployeeHasLeaveDAO {
    void assignLeaveForEmployee(int id, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    List<EmployeeHasLeaveDTO> getAllEmployeesLeaves();
}
