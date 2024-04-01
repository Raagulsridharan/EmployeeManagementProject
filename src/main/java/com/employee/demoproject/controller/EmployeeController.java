package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<EmployeeDTO>> getEmployee(){
        List<EmployeeDTO> el = employeeService.getEmp();
        return new ResponseEntity<>(el, HttpStatus.OK);
    }

    @GetMapping("/getEmp/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getEmpById(id),HttpStatus.OK);
    }

    @GetMapping("/getEmpCount")
    public Long getEmployeeCount(){
        return employeeService.getEmpCount();
    }
}
