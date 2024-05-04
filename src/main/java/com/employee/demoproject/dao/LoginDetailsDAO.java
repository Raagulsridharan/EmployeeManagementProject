package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.DataServiceException;

public interface LoginDetailsDAO {
    LoginDetails createLogin(Employee employee);
    void updateUserName(int id, EmployeeDTO employeeDTO);
    LoginDetails updatePassword(int id, LoginDetailsDTO loginDetailsDTO);
    LoginDetails employeeLogin(LoginDetails loginDetails) throws DataServiceException;
    LoginDetails activatingAccount(LoginDetailsDTO loginDetailsDTO);
    LoginDetailsDTO getEmployeeLoginByUsername(String username);
}
