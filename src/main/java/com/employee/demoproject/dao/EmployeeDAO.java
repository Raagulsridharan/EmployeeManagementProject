package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void createEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(int empId,EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(int empId);
    List<Employee> getAllEmployeeByDeptForRoleAssign(int deptId);
    List<Employee> getAllEmployeeByDeptForPayroll(int deptId);
    List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId);
    Long getTotalEmployeeCount();
    void deleteEmployee(int empId);
}
