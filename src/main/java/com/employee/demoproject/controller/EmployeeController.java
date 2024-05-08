package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmailSenderService;
import com.employee.demoproject.service.EmployeeService;
import jakarta.validation.Valid;
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

    @Autowired
    private EmailSenderService emailSenderService;


    /**
     * save employees for new
     * @param employeeDTO
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws BusinessServiceException, HttpClientException {
        LoginDetailsDTO loginDetailsDTO = employeeService.createEmployee(employeeDTO);
        if(loginDetailsDTO!=null){
            emailSenderService.sendEmail(loginDetailsDTO.getUsername(),loginDetailsDTO.getPassword(),loginDetailsDTO.getDeptId());
            System.out.println("************************[ Email Sent ]******************************");
            return new ResponseEntity<>(new HttpStatusResponse(loginDetailsDTO,HttpStatus.CREATED.value(), "Successfully employee created"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.BAD_REQUEST.value(), "Employee not created"),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * updating existing employee using id
     * @param empId
     * @param employeeDTO
     * @return
     * @throws BusinessServiceException
     */
    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> updateEmployee(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) throws BusinessServiceException {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(empId,employeeDTO);
        if(updatedEmployee!=null){
            return new ResponseEntity<>(new HttpStatusResponse(updatedEmployee,HttpStatus.OK.value(),"Employee Successfully updated"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NO_CONTENT.value(),"Error in Employee update method"),HttpStatus.NO_CONTENT);
        }
    }

    /**
     * updating employee with department and role/salary
     * @param empId
     * @param employeeDTO
     * @return
     * @throws BusinessServiceException
     */
    @PutMapping("/update/{empId}")
    public ResponseEntity<HttpStatusResponse> updateEmployeeDepartment(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) throws BusinessServiceException {
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeDepartment(empId,employeeDTO);
        if(updatedEmployee != null){
            return new ResponseEntity<>(new HttpStatusResponse(updatedEmployee,HttpStatus.OK.value(),"Employee Department Successfully updated"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NO_CONTENT.value(),"Error in Employee Department update method"),HttpStatus.NO_CONTENT);
        }
    }

    /**
     * getting all employee
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmployee() throws BusinessServiceException{
        return ofNullable(employeeService.getAllEmployee()).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Employees retrieved Successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));
    }

    /**
     * getting employee by id
     * @param empId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeById(@PathVariable int empId) throws BusinessServiceException {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(empId);
        if(employeeDTO!=null){
            return new ResponseEntity<>(new HttpStatusResponse(employeeService.getEmployeeById(empId),HttpStatus.OK.value(),"Employee retrieved"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"Employee not retrieved"),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getting all employees by department for role assigning
     * @param deptId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/role-assign/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForRoleAssign(@PathVariable int deptId) throws BusinessServiceException {
        //return new ResponseEntity<>(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForRoleAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for role assigning"),HttpStatus.OK);
        return ofNullable(employeeService.getAllEmployeeByDeptForRoleAssign(deptId)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Successfully Getting Employees for Role assign"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "No records found"), HttpStatus.NO_CONTENT));

    }

    /**
     * getting all employees by department for payroll assigning
     * @param deptId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/payroll/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForPayroll(@PathVariable int deptId) throws BusinessServiceException {
        //return new ResponseEntity<>(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForPayroll(deptId),HttpStatus.OK.value(),"Fetching employees by department for payroll"),HttpStatus.OK);
        return ofNullable(employeeService.getAllEmployeeByDeptForPayroll(deptId)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Successfully Getting Employees for Payroll"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "No records found"), HttpStatus.NO_CONTENT));

    }

    /**
     * getting all employees by department for leave assigning
     * @param deptId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/leave-assign/{deptId}")
    public ResponseEntity<HttpStatusResponse> getAllEmployeeByDeptForLeaveAssign(@PathVariable int deptId) throws BusinessServiceException {
        //return new ResponseEntity<>(new HttpStatusResponse(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId),HttpStatus.OK.value(),"Fetching employees by department for leave assigning"),HttpStatus.OK);
        return ofNullable(employeeService.getAllEmployeeByDeptForLeaveAssign(deptId)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTO -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTO, HttpStatus.OK.value(), "Successfully Getting Employees for Leave assign"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "No records found"), HttpStatus.NO_CONTENT));

    }

    /**
     * getting details of employee for card showing
     * @param empId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/card/{empId}")
    public ResponseEntity<HttpStatusResponse> employeeDetailCard(@PathVariable Integer empId) throws BusinessServiceException {
        LeaveAssignDTO leaveAssignDTO = employeeService.getEmployeeDetailCard(empId);
        if(leaveAssignDTO!=null){
            return new ResponseEntity<>(new HttpStatusResponse(leaveAssignDTO,HttpStatus.OK.value(), "Fetching employee details for card"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "Not getting employees details for card"),HttpStatus.NO_CONTENT);
        }
    }

    /**
     * fetching departments for pagination
     *
     * @param filterOption
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping("/all")
    public ResponseEntity<HttpStatusResponse> filterTheDepartment(@RequestBody @Valid FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(employeeService.filterEmployees(filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(employeeDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeDTOS, HttpStatus.OK.value(), "Employees filtered successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "no data to filter"), HttpStatus.NO_CONTENT));
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
}
