package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void createEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(int id,EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(int id);
    List<Employee> getAllEmployeeByDept(int deptId);
    Long getTotalEmployeeCount();
    void deleteEmployee(int id);
}
