package com.employee.demoproject.service;

import com.employee.demoproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    String createDepartment(Department department);
    void updateDepartment(Department department);
    List<Department> getAllDepartment();
    Department getDepartmentById(int id);
    Department getDepartmentByName(String name);
    Long getDepartmentCount();
}
