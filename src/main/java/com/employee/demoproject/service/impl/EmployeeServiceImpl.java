package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public LoginDetails createEmployee(EmployeeDTO employeeDTO) {
        try{
            return employeeDAO.createEmployee(employeeDTO);
        }catch (DataServiceException ex){
            throw new DataServiceException("Error saving employee data", ex);
        }
    }

    @Override
    public Employee updateEmployee(int empId, EmployeeDTO employeeDTO){
        return employeeDAO.updateEmployee(empId,employeeDTO);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employeeList = employeeDAO.getAllEmployee();
        return employeeList;
    }

    @Override
    public EmployeeDTO getEmployeeById(int empId) {
        return employeeDAO.getEmployeeById(empId);
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForRoleAssign(int deptId) {
        return employeeDAO.getAllEmployeeByDeptForRoleAssign(deptId);
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForPayroll(int deptId) {
        return employeeDAO.getAllEmployeeByDeptForPayroll(deptId);
    }

    @Override
    public List<Employee> getAllEmployeeByDeptForLeaveAssign(int deptId) {
        return employeeDAO.getAllEmployeeByDeptForLeaveAssign(deptId);
    }

    @Override
    public Long getEmpCount() {
        return employeeDAO.getTotalEmployeeCount();
    }

    @Override
    public void deleteEmployee(int empId) {
        employeeDAO.deleteEmployee(empId);
    }
}
