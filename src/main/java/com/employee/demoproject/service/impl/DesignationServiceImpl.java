package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DesignationDAO;
import com.employee.demoproject.dto.DesignationDTO;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.service.DesignationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationDAO designationDAO;

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
            throw new BusinessServiceException("Exception in Service layer to save the designation",e);
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

    private DesignationDTO mapToDTO(Designation designation){
        DesignationDTO designationDTO = new DesignationDTO();
        designationDTO.setRole(designation.getRole());
        designationDTO.setSalary_package(designation.getSalary_package());
        return designationDTO;
    }

    private Designation mapToEntity(DesignationDTO designationDTO){
        Designation designation = new Designation();
        designation.setRole(designationDTO.getRole());
        designation.setSalary_package(designationDTO.getSalary_package());
        return designation;
    }
}
