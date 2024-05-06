package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PayrollService {
    List<PayrollDTO> getEmployeePayroll(int empId);
    List<EmployeePaymentDTO> getAllEmployeePayroll();
    Payroll makePayment(int empId, PayrollDTO payrollDTO);
    PayrollDTO createPayroll(int empId) throws BusinessServiceException;
    Payroll updatePayroll(int empId, PayrollDTO payrollDTO);
    PaySlipDTO getPAYSlipContent(Integer payrollId);
    EmployeePaymentDTO getEmployeeSalaryDetails(int empId);
    Long totalPayrollCount() throws BusinessServiceException;
    List<EmployeePaymentDTO> filterPayroll(FilterOption filterOption) throws BusinessServiceException;



    Payroll getPaySlip(int payrollId);
    void uploadPaySlip(Integer payrollId, MultipartFile multipartFile);
    void generatePaySlip();
}
