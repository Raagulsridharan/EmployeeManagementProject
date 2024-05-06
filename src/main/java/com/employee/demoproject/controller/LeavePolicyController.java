package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LeavePolicyDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.LeavePolicyService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.LEAVE_POLICY)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeavePolicyController {

    @Autowired
    private LeavePolicyService leavePolicyService;

    /**
     * getting all leave policy
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllLeavePolicy() throws BusinessServiceException {
        return new ResponseEntity<>(new HttpStatusResponse(leavePolicyService.getAllLeavePolicy(), HttpStatus.OK.value(), "Fetching All leave types"),HttpStatus.OK);
    }

    @PostMapping
    public void createLeavePolicy(@RequestBody LeavePolicy leavePolicy) throws BusinessServiceException{
        leavePolicyService.createLeavePolicy(leavePolicy);
    }

    @GetMapping("/{id}")
    public LeavePolicy getLeavePolicyById(@PathVariable int id) throws BusinessServiceException{
        return leavePolicyService.getLeavePolicyById(id);
    }

    @GetMapping("/by/{name}")
    public LeavePolicy getLeavePolicyByName(@PathVariable String name) throws BusinessServiceException{
        return leavePolicyService.getLeavePolicyByName(name);
    }

    @GetMapping("/count")
    public Long getLeaveTypesCount() throws BusinessServiceException{
        return leavePolicyService.getLeaveTypesCount();
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeLeaves(@PathVariable Integer empId) throws BusinessServiceException{
        return ofNullable(leavePolicyService.getEmployeeLeave(empId)).filter(CollectionUtils::isNotEmpty)
                .map(leavePolicyDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(leavePolicyDTOS, HttpStatus.OK.value(), "Employees leave fetched successfully"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NO_CONTENT.value(), "no data to filter"), HttpStatus.NO_CONTENT));
    }
}
