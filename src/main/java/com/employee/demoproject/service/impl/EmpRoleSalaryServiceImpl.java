package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.EmpRoleSalaryDAO;
import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.EmpRoleSalaryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmpRoleSalaryServiceImpl implements EmpRoleSalaryService {

    @Autowired
    private EmpRoleSalaryDAO empRoleSalaryDAO;

    static Logger logger = Logger.getLogger(EmpRoleSalaryServiceImpl.class);

    @Override
    public List<EmployeePaymentDTO> getAllEmpRoleSalary() {
        return empRoleSalaryDAO.getAllEmpRoleSalary();
    }

    @Override
    public void createEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO) {
        empRoleSalaryDAO.createEmpRoleSalary(empId, empRoleSalaryDTO);
    }

    @Override
    public void updateEmpRoleSalary(int empId, EmpRoleSalaryDTO empRoleSalaryDTO) {
        empRoleSalaryDAO.updateEmpRoleSalary(empId,empRoleSalaryDTO);
    }

    @Override
    public EmployeePaymentDTO getRoleSalaryByEmployee(int empId) {
        return empRoleSalaryDAO.getRoleSalaryByEmployee(empId);
    }

    @Override
    public Long totalRoleSalaryCount() throws BusinessServiceException {
        try {
            logger.info("getting count of EmpRoleSalary in service layer ");
            return empRoleSalaryDAO.totalRoleSalaryCount();
        }catch (DataServiceException e){
            logger.error("Error in getting count of EmpRoleSalary in service layer. "+e);
            throw new BusinessServiceException("Exception in getting count in service layer.",e);
        }
    }

    @Override
    public List<EmpRoleSalaryDTO> filterEmpRoleSalary(FilterOption filterOption) throws BusinessServiceException {
        try {
            logger.info("Entering the service layer for fetching EmpRoleSalary");
            List<EmpRoleSalary> empRoleSalaryList = empRoleSalaryDAO.filterEmpRoleSalary(filterOption);
            return Optional.ofNullable(empRoleSalaryList)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            logger.error("Error in fetching fetching EmpRoleSalary in service layer. "+e);
            throw new BusinessServiceException("Exception in fetching EmpRoleSalary in service layer",e);
        }
    }

    private EmpRoleSalaryDTO mapToDTO(EmpRoleSalary empRoleSalary){
        return new EmpRoleSalaryDTO(
                empRoleSalary.getEmployee_role_salary().getId(),
                empRoleSalary.getEmployee_role_salary().getName(),
                empRoleSalary.getEmployee_role_salary().getDepartment().getId(),
                empRoleSalary.getEmployee_role_salary().getDepartment().getName(),
                empRoleSalary.getDesignation().getRole(),
                String.valueOf(empRoleSalary.getAnnual_salary_pack())
        );
    }
}
