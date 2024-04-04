package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.EmpRoleSalaryDAO;
import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.service.EmpRoleSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpRoleSalaryServiceImpl implements EmpRoleSalaryService {

    @Autowired
    private EmpRoleSalaryDAO empRoleSalaryDAO;

    @Override
    public List<EmployeePaymentDTO> getAllEmpRoleSalary() {
        return empRoleSalaryDAO.getAllEmpRoleSalary();
    }

    @Override
    public void createEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO) {
        empRoleSalaryDAO.createEmpRoleSalary(empId, empRoleSalaryDTO);
    }
}
