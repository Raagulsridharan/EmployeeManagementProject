package com.employee.demoproject.dao;

import com.employee.demoproject.entity.Department;
import com.employee.demoproject.pagination.FilterOption;

import java.util.List;

public interface DepartmentDAO {
    String createDepartment(Department department);
    void updateDepartment(Department department);
    List<Department> getAllDepartment();
    Department getDepartmentById(int id);
    Department getDepartmentByName(String name);
    Long getDepartmentCount();
    List<Department> filterDepartment(FilterOption filterOption);
}
