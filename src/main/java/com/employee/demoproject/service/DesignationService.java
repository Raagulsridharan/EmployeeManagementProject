package com.employee.demoproject.service;

import com.employee.demoproject.entity.Designation;

import java.util.List;

public interface DesignationService {
    void createDesignation(Designation designation);
    void updateDesignation(int id, Designation designation);
    List<Designation> getAllDesignation();
    Designation getDesignationById(int id);
    Designation getDesignationByName(String role);
    Long getDesignationCount();
}
