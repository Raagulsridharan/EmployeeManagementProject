package com.employee.demoproject.service;

import com.employee.demoproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    void createDept(Department department);
    void updateDept(Department department);
    List<Department> getAllDept();
    Department getDeptById(int id);
    Department getDeptByName(String name);
    Long getDeptCount();
}
