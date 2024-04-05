package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.EmployeeHasLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeHasLeave")
public class EmployeeHasLeaveController {

    @Autowired
    private EmployeeHasLeaveService employeeHasLeaveService;

    @PostMapping("/assignLeaveForEmployee/{empId}")
    public String assignLeaveForEmployee(@PathVariable int empId, @RequestBody List<EmployeeHasLeaveDTO> employeeHasLeaveDTOS){
        employeeHasLeaveService.assignLeaveForEmployee(empId,employeeHasLeaveDTOS);
        return "successfully assigned";
    }

    @GetMapping("/getAllEmpLeave")
    public ResponseEntity<List<EmployeeHasLeaveDTO>> getAllEmployeeLeaves(){
        return new ResponseEntity<>(employeeHasLeaveService.getAllEmployeeLeaves(), HttpStatus.OK);
    }

}
