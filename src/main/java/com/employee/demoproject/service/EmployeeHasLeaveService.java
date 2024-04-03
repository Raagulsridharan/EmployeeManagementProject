package com.employee.demoproject.service;

import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;

import java.util.List;

public interface EmployeeHasLeaveService {
    List<EmployeeHasLeave> getAllEmployeeLeaves();
}
