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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmp")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.saveEmp(employeeDTO);
        return "Employee created...!!!";
    }

    @PutMapping("/updateEmp/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmp(id,employeeDTO);
        return "Successfully updated...";
    }

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<EmployeeDTO>> getEmployee(){
        List<EmployeeDTO> el = employeeService.getEmp();
        return new ResponseEntity<>(el, HttpStatus.OK);
    }

    @GetMapping("/getEmp/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getEmpById(id),HttpStatus.OK);
    }

    @GetMapping("/getAllEmpByDept/{deptId}")
    public ResponseEntity<List<Employee>> getAllEmployeeByDept(@PathVariable int deptId){
        return new ResponseEntity<>(employeeService.getEmpByDept(deptId),HttpStatus.OK);
    }

    @GetMapping("/getEmpCount")
    public Long getEmployeeCount(){
        return employeeService.getEmpCount();
    }

    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeService.deleteEmp(id);
        return "Employee deleted...!!!";
    }
}
