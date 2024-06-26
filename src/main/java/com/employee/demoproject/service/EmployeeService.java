package com.employee.demoproject.service;

import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface EmployeeService {
    LoginDetailsDTO createEmployee(EmployeeDTO employeeDTO) throws BusinessServiceException, HttpClientException;
    EmployeeDTO updateEmployee(int empId, EmployeeDTO employeeDTO) throws BusinessServiceException ;
    EmployeeDTO updateEmployeeDepartment(int empId, EmployeeDTO employeeDTO) throws BusinessServiceException;
    List<EmployeeDTO> getAllEmployee() throws BusinessServiceException;
    EmployeeDTO getEmployeeById(int id) throws BusinessServiceException ;
    List<EmployeeDTO> getAllEmployeeByDeptForRoleAssign(int deptId) throws BusinessServiceException ;
    List<EmployeeDTO> getAllEmployeeByDeptForPayroll(int deptId) throws BusinessServiceException ;
    List<EmployeeDTO> getAllEmployeeByDeptForLeaveAssign(int deptId) throws BusinessServiceException ;
    Long getEmpCount() throws BusinessServiceException ;
    void deleteEmployee(int empId) throws BusinessServiceException ;
    LeaveAssignDTO getEmployeeDetailCard(int empId) throws BusinessServiceException;
    List<EmployeeDTO> filterEmployees(FilterOption filterOption) throws BusinessServiceException;
}
