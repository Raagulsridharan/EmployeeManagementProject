package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface EmployeeHasLeaveDAO {
    void assignLeaveForEmployee(int id);
    List<EmployeeHasLeave> getAllEmployeesLeaves();
}
