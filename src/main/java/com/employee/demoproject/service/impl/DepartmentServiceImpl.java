package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    public DepartmentDAO departmentDAO;

    @Override
    public String createDepartment(Department department) {
        return departmentDAO.createDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDAO.updateDepartment(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentDAO.getAllDepartment();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }

    @Override
    public Long getDepartmentCount() {
        return departmentDAO.getDepartmentCount();
    }
}
