package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.service.EmpRoleSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empRoleSalary")
public class EmpRoleSalaryController {

    @Autowired
    private EmpRoleSalaryService empRoleSalaryService;

    @GetMapping("/getAllEmpRoleSalary")
    public ResponseEntity<List<EmployeePaymentDTO>> getAllEmpRoleSalary(){
        return new ResponseEntity<>(empRoleSalaryService.getAllEmpRoleSalary(), HttpStatus.OK);
    }

    @PostMapping("/save/{empId}")
    public String createEmpRoleSalary(@PathVariable int empId,@RequestBody EmpRoleSalaryDTO empRoleSalaryDTO){
        empRoleSalaryService.createEmpRoleSalary(empId,empRoleSalaryDTO);
        return "Saved...";
    }
}

