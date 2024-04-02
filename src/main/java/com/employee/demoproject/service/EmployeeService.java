package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void saveEmp(EmployeeDTO employeeDTO);
    void updateEmp(int id, EmployeeDTO employeeDTO);
    List<EmployeeDTO> getEmp();
    EmployeeDTO getEmpById(int id);
    Long getEmpCount();
    void deleteEmp(int id);
}
