package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmpRoleSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseAPI.EMPLOYEE_ROLE_SALARY)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpRoleSalaryController {

    @Autowired
    private EmpRoleSalaryService empRoleSalaryService;

    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmpRoleSalary(){
        return ResponseEntity.ok(new HttpStatusResponse(empRoleSalaryService.getAllEmpRoleSalary(), HttpStatus.OK.value(),"Employees are fetched with role & salary details"));
    }

    @PostMapping("/{empId}")
    public void createEmpRoleSalary(@PathVariable int empId,@RequestBody EmpRoleSalaryDTO empRoleSalaryDTO){
        empRoleSalaryService.createEmpRoleSalary(empId,empRoleSalaryDTO);
    }

    @PutMapping("/{empId}")
    public void updateEmpRoleSalary(@PathVariable int empId,@RequestBody EmpRoleSalaryDTO empRoleSalaryDTO){
        empRoleSalaryService.updateEmpRoleSalary(empId,empRoleSalaryDTO);
    }

    @GetMapping("/{empId}")
    public EmployeePaymentDTO getRoleSalaryByEmployee(@PathVariable int empId){
        return empRoleSalaryService.getRoleSalaryByEmployee(empId);
    }
}

