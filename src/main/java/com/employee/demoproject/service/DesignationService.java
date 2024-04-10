package com.employee.demoproject.service;

import com.employee.demoproject.entity.Designation;

import java.util.List;

public interface DesignationService {
    void createDesignation(Designation designation);
    void updateDesignation(int id, Designation designation);
    List<Designation> getAllDesignation();
    Designation getDesignationById(int id);
    Designation getDesignationByRole(String role);
    Long getDesignationCount();
    String getDesignationByEmail(String email);
}
