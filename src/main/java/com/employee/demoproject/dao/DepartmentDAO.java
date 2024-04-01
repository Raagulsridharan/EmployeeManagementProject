package com.employee.demoproject.dao;

import com.employee.demoproject.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    void createDepartment(Department department);
    void updateDepartment(Department department);
    List<Department> getAllDepartment();
    Department getDepartmentById(int id);
}
