package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.Payroll;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PayrollDAO {
    List<PayrollDTO> getEmployeePayroll(int empId);
    List<EmployeePaymentDTO> getAllEmployeePayroll();
    Payroll makePayment(int empId, PayrollDTO payrollDTO);
    Payroll createPayroll(int empId);
    Payroll updatePayroll(int payrollId, PayrollDTO payrollDTO);
    PaySlipDTO getPAYSlipContent(Integer salaryId);
    EmployeePaymentDTO getEmployeeSalaryDetails(int empId);


    Payroll getPaySlip(int payrollId);
    void uploadPaySlip(Integer payrollId,MultipartFile multipartFile);
    void generatePaySlip();
}
