package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollDAO payrollDAO;

    @Override
    public List<PayrollDTO> getEmployeePayroll(int empId) {
        return payrollDAO.getEmployeePayroll(empId);
    }

    @Override
    public List<EmployeePaymentDTO> getAllEmployeePayroll() {
        return payrollDAO.getAllEmployeePayroll();
    }

    @Override
    public Payroll makePayment(int empId, PayrollDTO payrollDTO) {
        return payrollDAO.makePayment(empId,payrollDTO);
    }

    @Override
    public Payroll createPayroll(int empId) {
        return payrollDAO.createPayroll(empId);
    }

    @Override
    public Payroll updatePayroll(int empId, PayrollDTO payrollDTO) {
        return payrollDAO.updatePayroll(empId,payrollDTO);
    }

    @Override
    public PaySlipDTO getPAYSlipContent(Integer salaryId) {
        return payrollDAO.getPAYSlipContent(salaryId);
    }

    @Override
    public Payroll getPaySlip(int payrollId) {
        return payrollDAO.getPaySlip(payrollId);
    }

    @Override
    public void uploadPaySlip(Integer payrollId, MultipartFile multipartFile){
        payrollDAO.uploadPaySlip(payrollId,multipartFile);
    }

    @Override
    public void generatePaySlip() {
        payrollDAO.generatePaySlip();
    }
}
