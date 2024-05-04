package com.employee.demoproject.service;

import com.employee.demoproject.dto.DesignationDTO;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;

import java.util.List;

public interface DesignationService {
    DesignationDTO createDesignation(DesignationDTO designationDTO) throws BusinessServiceException;
    DesignationDTO updateDesignation(int id, DesignationDTO designationDTO) throws BusinessServiceException;
    List<Designation> getAllDesignation();
    Designation getDesignationById(int id);
    Designation getDesignationByRole(String role);
    Long getDesignationCount();
    String getDesignationByEmail(String email);
    List<DesignationDTO> getRolesByDepartment(Integer deptId) throws BusinessServiceException;
}
