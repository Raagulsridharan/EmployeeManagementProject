package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.HttpStatusEntity;
import com.employee.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseAPI.EMPLOYEES)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<HttpStatusEntity> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.createEmployee(employeeDTO),HttpStatus.CREATED.value(), "Successfully employee created"));
    }

    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatusEntity> updateEmployee(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.updateEmployee(empId,employeeDTO),HttpStatus.OK.value(),"Successfully updated"));
    }

    @GetMapping
    public ResponseEntity<HttpStatusEntity> getAllEmployee(){
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.getAllEmployee(), HttpStatus.CREATED.value(),"Employees retrieving Successfully"));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusEntity> getEmployeeById(@PathVariable int empId){
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.getEmployeeById(empId),HttpStatus.OK.value(),"Employee retrieved"));
    }

    @GetMapping("/getAllEmployeeByDeptForRoleAssign/{deptId}")
    public ResponseEntity<HttpStatusEntity> getAllEmployeeByDeptForRoleAssign(@PathVariable int deptId){
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.getAllEmployeeByDeptForRoleAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for role assigning"));
    }

    @GetMapping("/getAllEmpByDeptForPayroll/{deptId}")
    public ResponseEntity<HttpStatusEntity> getAllEmployeeByDeptForPayroll(@PathVariable int deptId){
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.getAllEmployeeByDeptForPayroll(deptId),HttpStatus.OK.value(),"Fetching employees by department for payroll"));
    }

    @GetMapping("/getAllEmployeeByDeptForLeaveAssign/{deptId}")
    public ResponseEntity<HttpStatusEntity> getAllEmployeeByDeptForLeaveAssign(@PathVariable int deptId){
        return ResponseEntity.ok(new HttpStatusEntity(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for leave assigning"));
    }

    @GetMapping("/getEmpCount")
    public Long getEmployeeCount(){
        return employeeService.getEmpCount();
    }

    @DeleteMapping("/{empId}")
    public void deleteEmployee(@PathVariable int empId){
        employeeService.deleteEmployee(empId);
    }
}
