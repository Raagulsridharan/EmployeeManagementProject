package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface EmployeeHasLeaveDAO {
    void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO);
    void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList);
    List<LeaveAssignDTO> getAllEmployeesLeaves();
    List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId);

    Long totalEmployeeHasLeave() throws DataServiceException;
    List<LeaveAssignDTO> filterEmployeeHasLeave(FilterOption filterOption) throws DataServiceException;
}
