package com.employee.demoproject.controller;

import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.EmployeeHasLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employeeHasLeave")
public class EmployeeHasLeaveController {

    @Autowired
    private EmployeeHasLeaveService employeeHasLeaveService;

    @GetMapping("/getAllEmpLeave")
    public ResponseEntity<List<EmployeeHasLeave>> getAllEmployeeLeaves(){
        return new ResponseEntity<>(employeeHasLeaveService.getAllEmployeeLeaves(), HttpStatus.OK);
    }

}
