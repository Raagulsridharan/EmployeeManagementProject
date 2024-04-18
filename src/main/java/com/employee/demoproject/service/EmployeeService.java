package com.employee.demoproject.service;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;

import java.util.List;

public interface EmployeeService {
    LoginDetails createEmployee(EmployeeDTO employeeDTO);
    Employee updateEmployee(int empId, EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(int id);
    List<Employee> getAllEmployeeByDeptForRoleAssign(int deptId);
    List<Employee> getAllEmployeeByDeptForPayroll(int deptId);
    List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId);
    Long getEmpCount();
    void deleteEmployee(int empId);
}
