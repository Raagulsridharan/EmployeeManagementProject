package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.EmpRoleSalary;

import java.util.List;

public interface EmpRoleSalaryDAO {
    List<EmployeePaymentDTO> getAllEmpRoleSalary();
    void createEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO);
    void updateEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO);
    EmployeePaymentDTO getRoleSalaryByEmployee(int empId);
}
