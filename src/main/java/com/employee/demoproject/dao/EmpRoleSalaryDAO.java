package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.entity.EmpRoleSalary;

import java.util.List;

public interface EmpRoleSalaryDAO {
    List<EmpRoleSalary> getAllEmpRoleSalary();
    void createEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO);
}
