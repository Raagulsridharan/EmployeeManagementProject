package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LoginDetailsDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginDetailsServiceImpl implements LoginDetailsService {
    @Autowired
    private LoginDetailsDAO loginDetailsDAO;

    @Override
    public void createLogin(Employee employee) {
        loginDetailsDAO.createLogin(employee);
    }

    @Override
    public void updateUserName(int id, EmployeeDTO employeeDTO) {
        loginDetailsDAO.updateUserName(id,employeeDTO);
    }

    @Override
    public void updatePassword(int id, String password) {
        loginDetailsDAO.updatePassword(id,password);
    }
}
