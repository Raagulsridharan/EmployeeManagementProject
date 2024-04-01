package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmp();
    EmployeeDTO getEmpById(int id);
    Long getEmpCount();
    void deleteEmp(int id);
}
