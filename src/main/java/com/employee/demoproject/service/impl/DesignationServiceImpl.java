package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.dao.DesignationDAO;
import com.employee.demoproject.dto.DesignationDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.service.DesignationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationDAO designationDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    static Logger logger =  Logger.getLogger(DesignationServiceImpl.class);

    /**
     * creating designation
     * @param designationDTO
     * @return
     * @throws BusinessServiceException
     */

    @Override
    public DesignationDTO createDesignation(DesignationDTO designationDTO) throws BusinessServiceException {
        try {
            logger.info("Entering to save designation in service layer");
            return mapToDTO(designationDAO.createDesignation(mapToEntity(designationDTO)));
        }catch (DataServiceException e){
            logger.error("Error in Service layer to save the designation. "+e);
            throw new BusinessServiceException("Duplicate Entry !!!",e);
        }

    }

    /**
     * updating designation using existing id
     * @param id
     * @param designationDTO
     * @return
     * @throws BusinessServiceException
     */

    @Override
    public DesignationDTO updateDesignation(int id, DesignationDTO designationDTO) throws BusinessServiceException{
        try {
            logger.info("Entering the method for updating designation.");
            return mapToDTO(designationDAO.updateDesignation(id,mapToEntity(designationDTO)));
        }catch (DataServiceException e){
            logger.error("Error in updating designation in service. "+e);
            throw new BusinessServiceException("Exception in updating designation in service. ",e);
        }
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

    @Override
    public List<DesignationDTO> getRolesByDepartment(Integer deptId) throws BusinessServiceException {
        try {
            logger.info("Entering the service for fetching designations by department");
            List<Designation> designations = designationDAO.getRolesByDepartment(deptId);
            return Optional.ofNullable(designations)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            logger.error("Error in fetching designation by department in service"+e);
            throw new BusinessServiceException("Exception in fetching designation by department in service",e);
        }
    }

    private DesignationDTO mapToDTO(Designation designation){
        DesignationDTO designationDTO = new DesignationDTO();
        if(designation.getId()!=null){
            designationDTO.setId(designation.getId());
        }
        designationDTO.setRole(designation.getRole());
        designationDTO.setSalary_package(designation.getSalary_package());
        designationDTO.setDepartmentId(designation.getDepartment().getId());
        return designationDTO;
    }

    private Designation mapToEntity(DesignationDTO designationDTO){
        Designation designation = new Designation();
        designation.setRole(designationDTO.getRole());
        designation.setSalary_package(designationDTO.getSalary_package());
        Department department = departmentDAO.getDepartmentById(designationDTO.getDepartmentId());
        designation.setDepartment(department);
        return designation;
    }
}
