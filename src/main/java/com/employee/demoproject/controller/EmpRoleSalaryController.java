package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmpRoleSalaryDTO;
import com.employee.demoproject.dto.EmployeePaymentDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmpRoleSalaryService;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.EMPLOYEE_ROLE_SALARY)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpRoleSalaryController {

    @Autowired
    private EmpRoleSalaryService empRoleSalaryService;

    /**
     * getting total count of employee role salary
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/count")
    public ResponseEntity<HttpStatusResponse> totalCountOfEmpRoleSalary() throws BusinessServiceException{
        Long count = empRoleSalaryService.totalRoleSalaryCount();
        if(count!=null){
            return new ResponseEntity<>(new HttpStatusResponse(count,HttpStatus.OK.value(),"Fetching count of EmpRoleSalary"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "Not gettign count of EmpRoleSalary"),HttpStatus.NO_CONTENT);
        }
    }

    /**
     * getting all employee role salary using filtering method
     * @param filterOption
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping("/all")
    public ResponseEntity<HttpStatusResponse> filterEmpRoleSalary(@RequestBody @Valid FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(empRoleSalaryService.filterEmpRoleSalary(filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(empRoleSalaryDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(empRoleSalaryDTOS, HttpStatus.OK.value(), "EmpRoleSalary filtered successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "no data to filter"), HttpStatus.NO_CONTENT));
    }

    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmpRoleSalary(){
        return new ResponseEntity<>(new HttpStatusResponse(empRoleSalaryService.getAllEmpRoleSalary(), HttpStatus.OK.value(),"Employees are fetched with role & salary details"),HttpStatus.OK);
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

