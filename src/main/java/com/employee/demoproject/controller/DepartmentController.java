package com.employee.demoproject.controller;

import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.HttpStatusEntity;
import com.employee.demoproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<HttpStatusEntity> getAllDepartment(){
        List<Department> departmentList = departmentService.getAllDepartment();

        if(departmentList!=null){
            return ResponseEntity.ok(new HttpStatusEntity(Optional.ofNullable(departmentList),HttpStatus.OK.value(),"Departments retrieved Successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HttpStatusEntity(Optional.ofNullable(departmentList),HttpStatus.NOT_FOUND.value(),"Not found"));
        }
    }

    /**
     * creating new department
     * @param department
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatusEntity> saveDepartment(@RequestBody Department department){
        String description = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new HttpStatusEntity(department, HttpStatus.CREATED.value(),description));
    }

    /**
     * Update or alter the department using their id
     * and assigning updated department object
     * @param id
     * @param department
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusEntity> updateDepartment(@PathVariable int id, @RequestBody Department department){
        Department updateDept = departmentService.getDepartmentById(id);
        updateDept.setName(department.getName());
        departmentService.updateDepartment(updateDept);
        return ResponseEntity.ok(new HttpStatusEntity(updateDept,HttpStatus.OK.value(),"Successfully updated"));
    }

    /**
     * getting single department using corresponding id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<HttpStatusEntity> getDeptById(@PathVariable int id){
        Department department = departmentService.getDepartmentById(id);
        if(department!=null){
            return ResponseEntity.ok(new HttpStatusEntity(Optional.ofNullable(department),HttpStatus.OK.value(),"Department retrieved Successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HttpStatusEntity(Optional.ofNullable(department),HttpStatus.NOT_FOUND.value(),"Department Not found"));
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
}
