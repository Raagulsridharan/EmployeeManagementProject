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
    public void createDept(Department department) {
        departmentDAO.createDepartment(department);
    }

    @Override
    public void updateDept(Department department) {
        departmentDAO.updateDepartment(department);
    }

    @Override
    public List<Department> getAllDept() {
        return departmentDAO.getAllDepartment();
    }

    @Override
    public Department getDeptById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public Department getDeptByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }

    @Override
    public Long getDeptCount() {
        return departmentDAO.getDepartmentCount();
    }
}
