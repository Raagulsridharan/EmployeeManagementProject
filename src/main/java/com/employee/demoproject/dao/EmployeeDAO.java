package com.employee.demoproject.dao;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface EmployeeDAO {
    LoginDetails createEmployee(Employee employee) throws DataServiceException, HttpClientException;
    Employee updateEmployee(int empId,EmployeeDTO employeeDTO) throws DataServiceException;
    Employee updateEmployeeDepartment(int empId,EmployeeDTO employeeDTO) throws DataServiceException;
    List<Employee> getAllEmployee() throws DataServiceException;
    Employee getEmployeeById(int empId) throws DataServiceException;
    List<Employee> getAllEmployeeByDeptForRoleAssign(int deptId) throws DataServiceException;
    List<Employee> getAllEmployeeByDeptForPayroll(int deptId) throws DataServiceException;
    List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId) throws DataServiceException;
    Long getTotalEmployeeCount() throws DataServiceException;
    void deleteEmployee(int empId) throws DataServiceException;
    LeaveAssignDTO getEmployeeDetailCard(int empId) throws DataServiceException;

    List<Employee> filterEmployee(FilterOption filterOption) throws DataServiceException;
}
