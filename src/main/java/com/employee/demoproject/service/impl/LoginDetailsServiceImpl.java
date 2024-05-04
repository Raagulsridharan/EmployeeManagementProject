package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.LoginDetailsDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
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
    public LoginDetailsDTO employeeLogin(LoginDetailsDTO loginDetailsDTO) throws BusinessServiceException {
        try {
            LoginDetails loginDetails = loginDetailsDAO.employeeLogin(mapToEntity(loginDetailsDTO));
            if(loginDetails!=null){
                return mapToDTO(loginDetails);
            }else {
                return null;
            }
        }catch (DataServiceException e){
            throw new BusinessServiceException("Exception in service for login",e);
        }
    }

    @Override
    public LoginDetailsDTO activatingAccount(LoginDetailsDTO loginDetailsDTO) {
        return mapToDTO(loginDetailsDAO.activatingAccount(loginDetailsDTO));
    }

//    private LoginDetailsDTO mapToDTO(LoginDetails loginDetails){
//        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
//        loginDetailsDTO.setEmpId(loginDetails.getEmployee_login().getId());
//        loginDetailsDTO.setFlag(loginDetails.getFlag());
//        return loginDetailsDTO;
//    }

    private LoginDetailsDTO mapToDTO(LoginDetails loginDetails){
        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
        loginDetailsDTO.setId(loginDetails.getId());
        loginDetailsDTO.setUsername(loginDetails.getUsername());
        loginDetailsDTO.setPassword(loginDetails.getPassword());
        loginDetailsDTO.setFlag(loginDetails.getFlag());
        loginDetailsDTO.setDeptId(loginDetails.getEmployee_login().getDepartment().getId());
        if(loginDetails.getActivated_on()!=null){
            loginDetailsDTO.setActivatedOn(loginDetails.getActivated_on());
        }
        loginDetailsDTO.setEmployee(loginDetails.getEmployee_login());
        loginDetailsDTO.setEmpId(loginDetails.getEmployee_login().getId());
        return loginDetailsDTO;
    }

    private LoginDetails mapToEntity(LoginDetailsDTO loginDetailsDTO){
        LoginDetails loginDetails = new LoginDetails();
        if(loginDetailsDTO.getUsername()!=null){
            loginDetails.setUsername(loginDetailsDTO.getUsername());
        }
        if(loginDetailsDTO.getPassword()!=null){
            loginDetails.setPassword(loginDetailsDTO.getPassword());
        }
        if(loginDetailsDTO.getFlag()!=null){
            loginDetails.setFlag(loginDetailsDTO.getFlag());
        }
        if(loginDetailsDTO.getActivatedOn()!=null){
            loginDetails.setActivated_on(loginDetailsDTO.getActivatedOn());
        }
        if(loginDetailsDTO.getEmployee()!=null){
            loginDetails.setEmployee_login(loginDetailsDTO.getEmployee());
        }
        return loginDetails;
    }
}
