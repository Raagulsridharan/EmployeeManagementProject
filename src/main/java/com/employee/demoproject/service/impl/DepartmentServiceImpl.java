package com.employee.demoproject.service.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return departmentDTO;
    }
}
