package com.employee.demoproject.dao;

import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface DepartmentDAO {
    Department createDepartment(String name) throws DataServiceException;
    Department updateDepartment(Integer id, String name) throws DataServiceException;
    List<Department> getAllDepartment() throws DataServiceException;
    Department getDepartmentById(int id) throws DataServiceException;
    Department getDepartmentByName(String name) throws DataServiceException;
    Long getDepartmentCount() throws DataServiceException;
    List<Department> filterDepartment(FilterOption filterOption) throws DataServiceException;
}
