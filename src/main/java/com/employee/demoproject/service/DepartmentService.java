package com.employee.demoproject.service;

import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface DepartmentService {
    String createDepartment(Department department);
    void updateDepartment(Department department);
    List<Department> getAllDepartment();
    Department getDepartmentById(int id);
    Department getDepartmentByName(String name);
    Long getDepartmentCount();
    List<DepartmentDTO> filterDepartment(FilterOption filterOption) throws BusinessServiceException;
}
