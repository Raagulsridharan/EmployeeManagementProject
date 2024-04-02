package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void createEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(int id,EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(int id);
    Long getTotalEmployeeCount();
    void deleteEmployee(int id);
}
