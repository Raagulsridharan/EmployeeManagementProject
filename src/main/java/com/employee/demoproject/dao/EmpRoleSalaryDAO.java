package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface EmpRoleSalaryDAO {
    List<EmployeePaymentDTO> getAllEmpRoleSalary();
    void createEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO);
    void updateEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO);
    EmployeePaymentDTO getRoleSalaryByEmployee(int empId);
    Long totalRoleSalaryCount() throws DataServiceException;
    List<EmpRoleSalary> filterEmpRoleSalary(FilterOption filterOption) throws DataServiceException;
}
