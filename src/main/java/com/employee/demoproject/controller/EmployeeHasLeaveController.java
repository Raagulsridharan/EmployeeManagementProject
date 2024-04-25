package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmployeeHasLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeHasLeave")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeHasLeaveController {

    @Autowired
    private EmployeeHasLeaveService employeeHasLeaveService;

    @PostMapping("/{empId}")
    public void assignLeaveForEmployee(@PathVariable int empId, @RequestBody List<EmployeeHasLeaveDTO> employeeHasLeaveDTOS){
        employeeHasLeaveService.assignLeaveForEmployee(empId,employeeHasLeaveDTOS);
    }

    @PutMapping("/{empId}")
    public void updateLeaveForEmployee(@PathVariable int empId, @RequestBody List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList){
        employeeHasLeaveService.updateLeaveForEmployee(empId,employeeHasLeaveDTOList);
        //return "leave updated...";
    }

    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmployeeLeaves(){
        return ResponseEntity.ok(new HttpStatusResponse(employeeHasLeaveService.getAllEmployeeLeaves(), HttpStatus.OK.value(),"Fetching All Employees Has Leave"));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeLeave(@PathVariable int empId){
        return ResponseEntity.ok(new HttpStatusResponse(employeeHasLeaveService.getEmployeeLeave(empId),HttpStatus.OK.value(),"Fetching employee leave details"));
    }

}
