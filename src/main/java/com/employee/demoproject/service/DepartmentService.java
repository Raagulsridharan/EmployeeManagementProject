package com.employee.demoproject.service;

import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO) throws BusinessServiceException, HttpClientException;
    DepartmentDTO updateDepartment(Integer id, DepartmentDTO departmentDTO) throws BusinessServiceException;
    List<DepartmentDTO> getAllDepartment() throws BusinessServiceException;
    Department getDepartmentById(int id) throws BusinessServiceException;
    Department getDepartmentByName(String name) throws BusinessServiceException;
    Long getDepartmentCount() throws BusinessServiceException;
    List<DepartmentDTO> filterDepartment(FilterOption filterOption) throws BusinessServiceException;
}
