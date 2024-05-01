package com.employee.demoproject.controller;

import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmployeeHasLeaveService;
import jakarta.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.EMPLOYEE_HAS_LEAVE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeHasLeaveController {

    @Autowired
    private EmployeeHasLeaveService employeeHasLeaveService;

    /**
     * getting count of total employees has leave
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/count")
    public ResponseEntity<HttpStatusResponse> totalEmployeeHasLeave() throws BusinessServiceException{
        Long count = employeeHasLeaveService.totalEmployeeHasLeave();
        if(count!=null){
            return new ResponseEntity<>(new HttpStatusResponse(count,HttpStatus.OK.value(), "Fetching the total employee has leave count"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "not found"),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getting all employees has leave using filterOption
     * @param filterOption
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> filterTheDepartment(@RequestBody @Valid FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(employeeHasLeaveService.filterEmployeeHasLeave(filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(employeeHasLeaveDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(employeeHasLeaveDTOS, HttpStatus.OK.value(), "Employees has leave filtered successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "no data to filter"), HttpStatus.NO_CONTENT));
    }

    /**
     * assigning leave for employee
     * @param empId
     * @param employeeHasLeaveDTOS
     * @throws BusinessServiceException
     */
    @PostMapping("/{empId}")
    public void assignLeaveForEmployee(@PathVariable int empId, @RequestBody List<EmployeeHasLeaveDTO> employeeHasLeaveDTOS) throws BusinessServiceException{
        employeeHasLeaveService.assignLeaveForEmployee(empId,employeeHasLeaveDTOS);
    }

    /**
     * updating leave for existing employee
     * @param empId
     * @param employeeHasLeaveDTOList
     * @throws BusinessServiceException
     */
    @PutMapping("/{empId}")
    public void updateLeaveForEmployee(@PathVariable int empId, @RequestBody List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList) throws BusinessServiceException{
        employeeHasLeaveService.updateLeaveForEmployee(empId,employeeHasLeaveDTOList);
    }

    /**
     * getting all employee leaves
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllEmployeeLeaves() throws BusinessServiceException{
        return new ResponseEntity<>(new HttpStatusResponse(employeeHasLeaveService.getAllEmployeeLeaves(), HttpStatus.OK.value(),"Fetching All Employees Has Leave"),HttpStatus.OK);
    }

    /**
     * getting individual employee leave by id
     * @param empId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeLeave(@PathVariable int empId) throws BusinessServiceException{
        return new ResponseEntity<>(new HttpStatusResponse(employeeHasLeaveService.getEmployeeLeave(empId),HttpStatus.OK.value(),"Fetching employee leave details"),HttpStatus.OK);
    }

}
