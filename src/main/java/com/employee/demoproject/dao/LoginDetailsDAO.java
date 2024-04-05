package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Employee;

public interface LoginDetailsDAO {
    void createLogin(Employee employee);
    void updateUserName(int id, EmployeeDTO employeeDTO);
    void updatePassword(int id, String password);
    Integer employeeLogin(LoginDetailsDTO loginDetailsDTO);
}
