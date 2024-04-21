package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.entity.EmpRoleSalary;
import com.employee.demoproject.entity.HttpStatusEntity;
import com.employee.demoproject.service.EmpRoleSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empRoleSalary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpRoleSalaryController {

    @Autowired
    private EmpRoleSalaryService empRoleSalaryService;

    @GetMapping
    public ResponseEntity<HttpStatusEntity> getAllEmpRoleSalary(){
        return ResponseEntity.ok(new HttpStatusEntity(empRoleSalaryService.getAllEmpRoleSalary(), HttpStatus.OK.value(),"Employees are fetched with role & salary details"));
    }

    @PostMapping("/{empId}")
    public void createEmpRoleSalary(@PathVariable int empId,@RequestBody EmpRoleSalaryDTO empRoleSalaryDTO){
        empRoleSalaryService.createEmpRoleSalary(empId,empRoleSalaryDTO);
        //return "Saved...";
    }

    @PutMapping("/{empId}")
    public void updateEmpRoleSalary(@PathVariable int empId,@RequestBody EmpRoleSalaryDTO empRoleSalaryDTO){
        empRoleSalaryService.updateEmpRoleSalary(empId,empRoleSalaryDTO);
        //return "Updated...";
    }

    @GetMapping("/{empId}")
    public EmployeePaymentDTO getRoleSalaryByEmployee(@PathVariable int empId){
        return empRoleSalaryService.getRoleSalaryByEmployee(empId);
    }
}

