package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartment(){
        List<Department> departmentList = departmentService.getAllDepartment();
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @PostMapping
    public String saveDepartment(@RequestBody Department department){
        departmentService.createDepartment(department);
        return "Successfully Department created";
    }

    @PutMapping("/{id}")
    public String updateDepartment(@PathVariable int id, @RequestBody Department department){
        Department updateDept = departmentService.getDepartmentById(id);
        updateDept.setName(department.getName());
        departmentService.updateDepartment(updateDept);
        return "updated successfully";
    }

    @GetMapping("/byId/{id}")
    public Department getDeptById(@PathVariable int id){
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/byName/{name}")
    public Department getDeptByName(@PathVariable String name){
        return departmentService.getDepartmentByName(name);
    }

    @GetMapping("/getDeptCount")
    public Long getDeptCount(){
        return departmentService.getDepartmentCount();
    }
}
