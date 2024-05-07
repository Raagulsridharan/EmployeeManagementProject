package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    public DepartmentDAO departmentDAO;

    static Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) throws BusinessServiceException, HttpClientException {
        try{
            logger.info("Entering the create department in service");
            return mapToDTO(departmentDAO.createDepartment(departmentDTO.getName()));
        }catch (DataServiceException e){
            logger.error("Error in create department in service. "+e);
            throw new BusinessServiceException(e.getMessage(),e);
        }
    }

    @Override
    public DepartmentDTO updateDepartment(Integer id, DepartmentDTO departmentDTO) throws BusinessServiceException{
        try{
            logger.info("Entering the method of update department in service.");
            return mapToDTO(departmentDAO.updateDepartment(id,departmentDTO.getName()));
        }catch (DataServiceException e){
            logger.error("Error in updating the department in service. "+e);
            throw new BusinessServiceException("Exception in service layer for updating department",e);
        }
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() throws BusinessServiceException{
        try{
            logger.info("getting all department in service.");
            List<Department> departmentList = departmentDAO.getAllDepartment();
            return Optional.ofNullable(departmentList)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            logger.error("Error in service layer for getting all department");
            throw new BusinessServiceException("Exception in service for getting all department");
        }

    }

    @Override
    public Department getDepartmentById(int id) throws BusinessServiceException{
        try{
            logger.info("Fetching department by id in service");
            return departmentDAO.getDepartmentById(id);
        }catch (Exception e){
            logger.error("Error in fetching department by id in service layer. "+e);
            throw new BusinessServiceException("Exception in service layer for getting department by id");
        }

    }

    @Override
    public Department getDepartmentByName(String name) throws BusinessServiceException{
        try{
            logger.info("Fetching department by it's name in service");
            return departmentDAO.getDepartmentByName(name);
        }catch (DataServiceException e){
            logger.error("Error in service layer for getting department by it's name. "+e);
            throw new BusinessServiceException("Exception in service layer for getting department by it's name.",e);
        }

    }

    @Override
    public Long getDepartmentCount() throws BusinessServiceException{
        try{
            logger.info("Getting department count in service");
            return departmentDAO.getDepartmentCount();
        }catch (DataServiceException e){
            logger.error("Error in getting department count in service layer");
            throw new BusinessServiceException("Exception in getting department count",e);
        }

    }

    @Override
    public List<DepartmentDTO> filterDepartment(FilterOption filterOption) throws BusinessServiceException{
        try{
            List<Department> departmentList = departmentDAO.filterDepartment(filterOption);
            return Optional.ofNullable(departmentList)
                    .map(list -> list.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList()))
                    .orElse(null);
        }catch (DataServiceException e){
            throw new BusinessServiceException("Exception in service layer",e);
        }

    }

    private DepartmentDTO mapToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setTotalCount(departmentDAO.getDepartmentCount());
        return departmentDTO;
    }

    private Department mapToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
        return department;
    }
}
