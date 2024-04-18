package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;

public interface LoginDetailsService {
    LoginDetails createLogin(Employee employee);
    void updateUserName(int id, EmployeeDTO employeeDTO);
    void updatePassword(int id, String password);
    LoginDetailsDTO employeeLogin(LoginDetailsDTO loginDetailsDTO);
    void activatingAccount(LoginDetailsDTO loginDetailsDTO);
}
