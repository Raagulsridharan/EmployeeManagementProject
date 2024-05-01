package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.PayrollService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollDAO payrollDAO;

    static Logger logger = Logger.getLogger(PayrollServiceImpl.class);

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
        return payrollDAO.makePayment(empId, payrollDTO);
    }

    @Override
    public Payroll createPayroll(int empId) {
        return payrollDAO.createPayroll(empId);
    }

    @Override
    public Payroll updatePayroll(int empId, PayrollDTO payrollDTO) {
        return payrollDAO.updatePayroll(empId, payrollDTO);
    }

    @Override
    public PaySlipDTO getPAYSlipContent(Integer salaryId) {
        return payrollDAO.getPAYSlipContent(salaryId);
    }

    @Override
    public EmployeePaymentDTO getEmployeeSalaryDetails(int empId) {
        return payrollDAO.getEmployeeSalaryDetails(empId);
    }

    @Override
    public Long totalPayrollCount() throws BusinessServiceException {
        try {
            logger.info("service layer for getting count of total payroll.");
            return payrollDAO.totalPayrollCount();
        } catch (DataServiceException e) {
            logger.error("Error in service layer for getting count of total payroll. " + e);
            throw new BusinessServiceException("Exception in service layer for getting count of total payroll.", e);
        }
    }

    @Override
    public List<EmployeePaymentDTO> filterPayroll(FilterOption filterOption) throws BusinessServiceException {
        try {
            List<Payroll> payrolls = payrollDAO.filterPayroll(filterOption);
            if (payrolls == null || payrolls.isEmpty()) {
                return null;
            }

            Set<Integer> addedEmpIds = new HashSet<>(); // HashSet to keep track of added empIds
            return payrolls.stream()
                    .map(p -> mapToDTO(p, addedEmpIds))
                    .filter(Objects::nonNull) // Remove null DTOs
                    .collect(Collectors.toList());
        } catch (DataServiceException e) {
            throw new BusinessServiceException("Exception in service layer for filtering payroll", e);
        }
    }

    //-----------------------------------------------------------------------
    @Override
    public Payroll getPaySlip(int payrollId) {
        return payrollDAO.getPaySlip(payrollId);
    }

    @Override
    public void uploadPaySlip(Integer payrollId, MultipartFile multipartFile) {
        payrollDAO.uploadPaySlip(payrollId, multipartFile);
    }

    @Override
    public void generatePaySlip() {
        payrollDAO.generatePaySlip();
    }


//    private PaySlipDTO mapToDTO(Payroll payroll){
//        PaySlipDTO paySlipDTO = new PaySlipDTO();
//
//        paySlipDTO.setEmpId(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getId());
//        paySlipDTO.setEmpName(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getName());
//        paySlipDTO.setDeptName(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getDepartment().getName());
//        paySlipDTO.setDesignation(payroll.getEmpRoleSalary_payroll().getDesignation().getRole());
//        paySlipDTO.setAnnualSalaryPack(payroll.getEmpRoleSalary_payroll().getAnnual_salary_pack());
//        paySlipDTO.setBasicSalary(payroll.getEmpRoleSalary_payroll().getBasic_sal_month());
//        paySlipDTO.setTaxReduction(payroll.getEmpRoleSalary_payroll().getTax_reduction_month());
//        paySlipDTO.setNetSalary(payroll.getEmpRoleSalary_payroll().getNet_sal_month());
//        paySlipDTO.setSalaryId(payroll.getEmpRoleSalary_payroll().getId());
//        paySlipDTO.setMonth(payroll.getMonth());
//
//        return paySlipDTO;
//    }

    private EmployeePaymentDTO mapToDTO(Payroll payroll, Set<Integer> addedEmpIds) {
        int empId = payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getId();
        if (addedEmpIds.contains(empId)) {
            // If empId already added, return null
            return null;
        }
        addedEmpIds.add(empId); // Add empId to set

        EmployeePaymentDTO employeePaymentDTO = new EmployeePaymentDTO();
        employeePaymentDTO.setEmpId(empId);
        employeePaymentDTO.setName(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getName());
        employeePaymentDTO.setDept(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getDepartment().getName());
        employeePaymentDTO.setRole(payroll.getEmpRoleSalary_payroll().getDesignation().getRole());
        employeePaymentDTO.setBasic_sal_month(payroll.getEmpRoleSalary_payroll().getBasic_sal_month());
        employeePaymentDTO.setTax_reduction_month(payroll.getEmpRoleSalary_payroll().getTax_reduction_month());
        employeePaymentDTO.setNet_sal_month(payroll.getEmpRoleSalary_payroll().getNet_sal_month());

        return employeePaymentDTO;
    }
}
