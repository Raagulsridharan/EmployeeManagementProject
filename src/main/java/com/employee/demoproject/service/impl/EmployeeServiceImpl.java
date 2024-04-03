package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;
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
    public void saveEmp(EmployeeDTO employeeDTO) {
        employeeDAO.createEmployee(employeeDTO);
    }

    @Override
    public void updateEmp(int id, EmployeeDTO employeeDTO) {
        employeeDAO.updateEmployee(id,employeeDTO);
    }

    @Override
    public List<EmployeeDTO> getEmp() {
        List<EmployeeDTO> employeeList = employeeDAO.getAllEmployee();
        return employeeList;
    }

    @Override
    public EmployeeDTO getEmpById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<Employee> getEmpByDept(int deptId) {
        return employeeDAO.getAllEmployeeByDept(deptId);
    }

    @Override
    public Long getEmpCount() {
        return employeeDAO.getTotalEmployeeCount();
    }

    @Override
    public void deleteEmp(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
