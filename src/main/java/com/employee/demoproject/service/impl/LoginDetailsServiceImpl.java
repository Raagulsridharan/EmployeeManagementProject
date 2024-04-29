package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LoginDetailsDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
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
    public LoginDetails createLogin(Employee employee) {
        return loginDetailsDAO.createLogin(employee);
    }

    @Override
    public void updateUserName(int id, EmployeeDTO employeeDTO) {
        loginDetailsDAO.updateUserName(id,employeeDTO);
    }

    @Override
    public LoginDetailsDTO updatePassword(int id, LoginDetailsDTO loginDetailsDTO) {
        return mapToDTO(loginDetailsDAO.updatePassword(id,loginDetailsDTO));
    }

    @Override
    public LoginDetailsDTO employeeLogin(LoginDetailsDTO loginDetailsDTO) {
        return loginDetailsDAO.employeeLogin(loginDetailsDTO);
    }

    @Override
    public LoginDetailsDTO activatingAccount(LoginDetailsDTO loginDetailsDTO) {
        return mapToDTO(loginDetailsDAO.activatingAccount(loginDetailsDTO));
    }

    private LoginDetailsDTO mapToDTO(LoginDetails loginDetails){
        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
        loginDetailsDTO.setEmpId(loginDetails.getEmployee_login().getId());
        loginDetailsDTO.setFlag(loginDetails.getFlag());
        return loginDetailsDTO;
    }
}
