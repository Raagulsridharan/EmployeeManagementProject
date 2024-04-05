package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PayrollDTO;

import java.util.List;

public interface PayrollDAO {
    List<PayrollDTO> getEmployeePayroll(int empId);
    List<EmployeePaymentDTO> getAllEmployeePayroll();
    void makePayment(int empId, PayrollDTO payrollDTO);
    void createPayroll(int empId);
}
