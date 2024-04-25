package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.responce.HttpStatusResponse;
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
    public ResponseEntity<HttpStatusResponse> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.createEmployee(employeeDTO),HttpStatus.CREATED.value(), "Successfully employee created"));
    }

    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> updateEmployee(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.updateEmployee(empId,employeeDTO),HttpStatus.OK.value(),"Employee Successfully updated"));
    }

    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmployee(){
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.getAllEmployee(), HttpStatus.CREATED.value(),"Employees retrieving Successfully"));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeById(@PathVariable int empId){
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.getEmployeeById(empId),HttpStatus.OK.value(),"Employee retrieved"));
    }

    @GetMapping("/getAllEmployeeByDeptForRoleAssign/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForRoleAssign(@PathVariable int deptId){
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForRoleAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for role assigning"));
    }

    @GetMapping("/getAllEmpByDeptForPayroll/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForPayroll(@PathVariable int deptId){
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForPayroll(deptId),HttpStatus.OK.value(),"Fetching employees by department for payroll"));
    }

    @GetMapping("/getAllEmployeeByDeptForLeaveAssign/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForLeaveAssign(@PathVariable int deptId){
        return ResponseEntity.ok(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for leave assigning"));
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
