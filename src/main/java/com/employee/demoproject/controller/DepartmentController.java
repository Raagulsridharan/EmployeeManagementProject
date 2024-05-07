package com.employee.demoproject.controller;

import com.employee.demoproject.dto.DepartmentDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.DepartmentService;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.DEPARTMENT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    /**
     * Getting all departments
     * from the department table
     * with HttpStatus code
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllDepartment() throws BusinessServiceException {
        return ofNullable(departmentService.getAllDepartment()).filter(CollectionUtils::isNotEmpty)
                .map(departmentDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(departmentDTO, HttpStatus.OK.value(), "Departments retrieved Successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));
    }

    /**
     * creating new department
     *
     * @param departmentDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> saveDepartment(@RequestBody DepartmentDTO departmentDTO) throws BusinessServiceException, HttpClientException {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        if (createdDepartment != null) {
            return new ResponseEntity<>(new HttpStatusResponse(createdDepartment, HttpStatus.CREATED.value(), "Successfully department created"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "Department Not updated"), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Update or alter the department using their id
     * and assigning updated department object
     *
     * @param id
     * @param departmentDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> updateDepartment(@PathVariable int id, @RequestBody DepartmentDTO departmentDTO) throws BusinessServiceException {
        DepartmentDTO departmentDTO1 = departmentService.updateDepartment(id, departmentDTO);
        if (departmentDTO1 != null) {
            return new ResponseEntity<>(new HttpStatusResponse(departmentDTO1, HttpStatus.OK.value(), "Department Updated Successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "Department Not updated"), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * getting single department using corresponding id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> getDeptById(@PathVariable int id) throws BusinessServiceException {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            return new ResponseEntity<>(new HttpStatusResponse(department, HttpStatus.OK.value(), "Department retrieved Successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "Department Not found"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getting all departments count
     *
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<HttpStatusResponse> getDeptCount() throws BusinessServiceException {
        Long count = departmentService.getDepartmentCount();
        if (count != null) {
            return new ResponseEntity<>(new HttpStatusResponse(count, HttpStatus.OK.value(), "Getting department count"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No departments for counting"), HttpStatus.NOT_FOUND);
        }
    }


    /**
     * fetching departments for pagination
     *
     * @param filterOption
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping("/all")
    public ResponseEntity<HttpStatusResponse> filterTheDepartment(@RequestBody @Valid FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(departmentService.filterDepartment(filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(departmentDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(departmentDTOS, HttpStatus.OK.value(), "Departments filtered successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "no data to filter"), HttpStatus.NO_CONTENT));
    }

}
