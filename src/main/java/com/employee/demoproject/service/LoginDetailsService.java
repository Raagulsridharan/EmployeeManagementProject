package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;

public interface LoginDetailsService {
    void createLogin(Employee employee);
    void updateUserName(int id, EmployeeDTO employeeDTO);
    void updatePassword(int id, String password);
}
