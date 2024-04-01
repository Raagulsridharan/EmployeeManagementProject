package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeDAO {
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(int id);
    Long getTotalEmployeeCount();
    void deleteEmployee(int id);
}
