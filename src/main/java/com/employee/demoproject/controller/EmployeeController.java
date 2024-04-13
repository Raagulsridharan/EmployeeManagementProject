package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.createEmployee(employeeDTO);
        return "Employee created...!!!";
    }

    @PutMapping("/updateEmp/{empId}")
    public String updateEmployee(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(empId,employeeDTO);
        return "Successfully updated...";
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        List<EmployeeDTO> el = employeeService.getAllEmployee();
        return new ResponseEntity<>(el, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int empId){
        return new ResponseEntity<>(employeeService.getEmployeeById(empId),HttpStatus.OK);
    }

    @GetMapping("/getAllEmployeeByDeptForRoleAssign/{deptId}")
    public ResponseEntity<List<Employee>> getAllEmployeeByDeptForRoleAssign(@PathVariable int deptId){
        return new ResponseEntity<>(employeeService.getAllEmployeeByDeptForRoleAssign(deptId),HttpStatus.OK);
    }

    @GetMapping("/getAllEmpByDeptForPayroll/{deptId}")
    public ResponseEntity<List<Employee>> getAllEmployeeByDeptForPayroll(@PathVariable int deptId){
        return new ResponseEntity<>(employeeService.getAllEmployeeByDeptForPayroll(deptId),HttpStatus.OK);
    }

    @GetMapping("/getAllEmployeeByDeptForLeaveAssign/{deptId}")
    public ResponseEntity<List<Employee>> getAllEmployeeByDeptForLeaveAssign(@PathVariable int deptId){
        return new ResponseEntity<>(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId),HttpStatus.OK);
    }

    @GetMapping("/getEmpCount")
    public Long getEmployeeCount(){
        return employeeService.getEmpCount();
    }

    @DeleteMapping("/{empId}")
    public String deleteEmployee(@PathVariable int empId){
        employeeService.deleteEmployee(empId);
        return "Employee deleted...!!!";
    }
}
