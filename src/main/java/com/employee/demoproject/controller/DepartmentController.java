package com.employee.demoproject.controller;

import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.DepartmentService;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.DEPARTMENT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    /** Getting all departments
     *  from the department table
     *  with HttpStatus code
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllDepartment(){
        return ofNullable(departmentService.getAllDepartment()).filter(CollectionUtils::isNotEmpty)
                .map(departments -> new ResponseEntity<>
                        (new HttpStatusResponse(departments,HttpStatus.OK.value(),"Departments retrieved Successfully"),HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"No records found"),HttpStatus.NOT_FOUND));
    }

    /**
     * creating new department
     * @param department
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> saveDepartment(@RequestBody Department department){
        String description = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new HttpStatusResponse(department, HttpStatus.CREATED.value(),description));
    }

    /**
     * Update or alter the department using their id
     * and assigning updated department object
     * @param id
     * @param department
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> updateDepartment(@PathVariable int id, @RequestBody Department department){
        Department updateDept = departmentService.getDepartmentById(id);
        updateDept.setName(department.getName());
        departmentService.updateDepartment(updateDept);
        return ResponseEntity.ok(new HttpStatusResponse(updateDept,HttpStatus.OK.value(),"Successfully updated"));
    }

    /**
     * getting single department using corresponding id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> getDeptById(@PathVariable int id){
        Department department = departmentService.getDepartmentById(id);
        if(department!=null){
            return ResponseEntity.ok(new HttpStatusResponse(ofNullable(department),HttpStatus.OK.value(),"Department retrieved Successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HttpStatusResponse(ofNullable(department),HttpStatus.NOT_FOUND.value(),"Department Not found"));
        }
    }

//    @GetMapping("/{name}")
//    public Department getDeptByName(@PathVariable String name){
//        return departmentService.getDepartmentByName(name);
//    }


    /**
     * getting all departments count
     * @return
     */
    @GetMapping("/getDeptCount")
    public Long getDeptCount(){
        return departmentService.getDepartmentCount();
    }

    @PostMapping("filter-option")
    public ResponseEntity<HttpStatusResponse> filterTheDepartment(@RequestBody @Valid FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(departmentService.filterDepartment(filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(departmentDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(departmentDTOS,HttpStatus.OK.value(), "Departments filtered successfully"),HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NO_CONTENT.value(), "no data to filter"),HttpStatus.NO_CONTENT));
    }

}
