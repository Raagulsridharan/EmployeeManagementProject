package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmployeeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.EMPLOYEES)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<HttpStatusResponse> saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws BusinessServiceException {
        LoginDetails loginDetails = employeeService.createEmployee(employeeDTO);
        if(loginDetails!=null){
            return new ResponseEntity<>(new HttpStatusResponse(loginDetails,HttpStatus.CREATED.value(), "Successfully employee created"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.BAD_REQUEST.value(), "Employee not created"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> updateEmployee(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) throws BusinessServiceException {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(empId,employeeDTO);
        if(updatedEmployee!=null){
            return new ResponseEntity<>(new HttpStatusResponse(updatedEmployee,HttpStatus.OK.value(),"Employee Successfully updated"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NO_CONTENT.value(),"Error in Employee update method"),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmployee() throws BusinessServiceException{
        return ofNullable(employeeService.getAllEmployee()).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Employees retrieved Successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeById(@PathVariable int empId) throws BusinessServiceException {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(empId);
        if(employeeDTO!=null){
            return new ResponseEntity<>(new HttpStatusResponse(employeeService.getEmployeeById(empId),HttpStatus.OK.value(),"Employee retrieved"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"Employee not retrieved"),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/role-assign/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForRoleAssign(@PathVariable int deptId) throws BusinessServiceException {
        //return new ResponseEntity<>(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForRoleAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for role assigning"),HttpStatus.OK);
        return ofNullable(employeeService.getAllEmployeeByDeptForRoleAssign(deptId)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Successfully Getting Employees for Role assign"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("/payroll/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForPayroll(@PathVariable int deptId) throws BusinessServiceException {
        //return new ResponseEntity<>(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForPayroll(deptId),HttpStatus.OK.value(),"Fetching employees by department for payroll"),HttpStatus.OK);
        return ofNullable(employeeService.getAllEmployeeByDeptForPayroll(deptId)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Successfully Getting Employees for Payroll"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("/leave-assign/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForLeaveAssign(@PathVariable int deptId) throws BusinessServiceException {
        //return new ResponseEntity<>(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for leave assigning"),HttpStatus.OK);
        return ofNullable(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Successfully Getting Employees for Leave assign"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("/count")
    public Long getEmployeeCount() throws BusinessServiceException {
        Long count = employeeService.getEmpCount();
        if(count!=null){
            return employeeService.getEmpCount();
        }else {
            return null;
        }
    }

    @DeleteMapping("/{empId}")
    public void deleteEmployee(@PathVariable int empId) throws BusinessServiceException {
        employeeService.deleteEmployee(empId);
    }

    @GetMapping("/card/{empId}")
    public ResponseEntity<HttpStatusResponse> employeeDetailCard(@PathVariable Integer empId) throws BusinessServiceException {
        LeaveAssignDTO leaveAssignDTO = employeeService.getEmployeeDetailCard(empId);
        if(leaveAssignDTO!=null){
            return new ResponseEntity<>(new HttpStatusResponse(leaveAssignDTO,HttpStatus.OK.value(), "Fetching employee details for card"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "Not getting employees details for card"),HttpStatus.NO_CONTENT);
        }
    }
}
