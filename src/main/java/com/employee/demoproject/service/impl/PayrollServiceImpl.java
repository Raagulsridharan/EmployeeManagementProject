package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.PayrollDAO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PaySlipDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.Payroll;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
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
    public PayrollDTO makePayment(int empId, PayrollDTO payrollDTO) throws BusinessServiceException, HttpClientException{
        try {
            logger.info("Entering to payment");
            return mapToDTO(payrollDAO.makePayment(empId, payrollDTO));
        }catch (DataServiceException e){
            logger.error("Error in payment, "+e);
            throw new BusinessServiceException(e.getMessage(),e);
        }
    }

    @Override
    public PayrollDTO createPayroll(int empId) throws BusinessServiceException{
        try {
            return mapToDTO(payrollDAO.createPayroll(empId));
        }catch (DataServiceException e){
            throw new BusinessServiceException("Already Paid for this month !",e);
        }
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
            List<EmpRoleSalary> payrolls = payrollDAO.filterPayroll(filterOption);
            if (payrolls == null || payrolls.isEmpty()) {
                return null;
            }

            Set<Integer> addedEmpIds = new HashSet<>();
            return payrolls.stream()
                    .map(p -> mapToDTOEmployeePaymentDTO(p, addedEmpIds))
                    .filter(Objects::nonNull)
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

    private EmployeePaymentDTO mapToDTOEmployeePaymentDTO(EmpRoleSalary payroll, Set<Integer> addedEmpIds) {
        int empId = payroll.getEmployee_role_salary().getId();
        if (addedEmpIds.contains(empId)) {
            return null;
        }
        addedEmpIds.add(empId);

        EmployeePaymentDTO employeePaymentDTO = new EmployeePaymentDTO();
        employeePaymentDTO.setEmpId(empId);
        employeePaymentDTO.setName(payroll.getEmployee_role_salary().getName());
        employeePaymentDTO.setDept(payroll.getEmployee_role_salary().getDepartment().getName());
        employeePaymentDTO.setRole(payroll.getDesignation().getRole());
        employeePaymentDTO.setBasic_sal_month(payroll.getBasic_sal_month());
        employeePaymentDTO.setTax_reduction_month(payroll.getTax_reduction_month());
        employeePaymentDTO.setNet_sal_month(payroll.getNet_sal_month());

        return employeePaymentDTO;
    }
     private PayrollDTO mapToDTO(Payroll payroll){
        PayrollDTO payrollDTO = new PayrollDTO();
        payrollDTO.setId(payroll.getId());
        payrollDTO.setEmpId(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getId());
        payrollDTO.setName(payroll.getEmpRoleSalary_payroll().getEmployee_role_salary().getName());
        payrollDTO.setMonth(payroll.getMonth());
        payrollDTO.setPaid_salary(payroll.getPaid_salary());
        payrollDTO.setDescription(payroll.getDescription());
        payrollDTO.setNet_sal_month(payroll.getEmpRoleSalary_payroll().getNet_sal_month());
        payrollDTO.setStatus(payroll.getStatus());
        return payrollDTO;
     }
}
