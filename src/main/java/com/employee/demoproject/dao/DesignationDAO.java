package com.employee.demoproject.dao;

import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.exceptions.DataServiceException;

import java.util.List;

public interface DesignationDAO {
    Designation createDesignation(Designation designation);
    Designation updateDesignation(int id, Designation designation) throws DataServiceException;
    List<Designation> getAllDesignation();
    Designation getDesignationById(int id);
    Designation getDesignationByRole(String role);
    Long getDesignationCount();
    String getDesignationByEmail(String email);
    List<Designation> getRolesByDepartment(Integer deptId) throws DataServiceException;
}
