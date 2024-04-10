package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DesignationDAO;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationDAO designationDAO;

    @Override
    public void createDesignation(Designation designation) {
        designationDAO.createDesignation(designation);
    }

    @Override
    public void updateDesignation(int id, Designation designation) {
        designationDAO.updateDesignation(id,designation);
    }

    @Override
    public List<Designation> getAllDesignation() {
        return designationDAO.getAllDesignation();
    }

    @Override
    public Designation getDesignationById(int id) {
        return designationDAO.getDesignationById(id);
    }

    @Override
    public Designation getDesignationByRole(String role) {
        return designationDAO.getDesignationByRole(role);
    }

    @Override
    public Long getDesignationCount() {
        return designationDAO.getDesignationCount();
    }

    @Override
    public String getDesignationByEmail(String email) {
        return designationDAO.getDesignationByEmail(email);
    }
}
