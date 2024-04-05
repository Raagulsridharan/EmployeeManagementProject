package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.dto.PayrollDTO;
import com.employee.demoproject.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/getEmployeePayroll/{empId}")
    public ResponseEntity<List<PayrollDTO>> getEmployeePayroll(@PathVariable int empId){
        return new ResponseEntity<>(payrollService.getEmployeePayroll(empId), HttpStatus.OK);
    }

    @GetMapping("/getAllEmployeePayroll")
    public ResponseEntity<List<EmployeePaymentDTO>> getEmployeePayroll(){
        return new ResponseEntity<>(payrollService.getAllEmployeePayroll(), HttpStatus.OK);
    }

    @PostMapping("/makePayment/{empId}")
    public String makePayment(@PathVariable int empId, @RequestBody PayrollDTO payrollDTO){
        payrollService.makePayment(empId,payrollDTO);
        return "Salary credited...!!!";
    }

    @PostMapping("/createPayroll/{empId}")
    public String createPayroll(@PathVariable int empId){
        payrollService.createPayroll(empId);
        return "Payroll created...!";
    }
}
