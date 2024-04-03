package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.entity.EmpRoleSalary;

import java.util.List;

public interface EmpRoleSalaryService {
    List<EmpRoleSalary> getAllEmpRoleSalary();
    void createEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO);
}
