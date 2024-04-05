package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void makePayment(int empId, PayrollDTO payrollDTO) {
        payrollDAO.makePayment(empId,payrollDTO);
    }

    @Override
    public void createPayroll(int empId) {
        payrollDAO.createPayroll(empId);
    }
}
