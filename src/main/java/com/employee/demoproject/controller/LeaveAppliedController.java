package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.service.LeaveAppliedService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping(BaseAPI.EMPLOYEE_LEAVE_APPLIED)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeaveAppliedController {

    @Autowired
    private LeaveAppliedService leaveAppliedService;

    /**
     * getting count of employee leave history
     * @param empId
     * @return
     * @throws BusinessServiceException
     */
    @GetMapping("/count/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeLeaveHistoryCount(@PathVariable int empId) throws BusinessServiceException{
        Long count = leaveAppliedService.getEmployeeLeaveHistoryCount(empId);
        if(count!=null){
            return new ResponseEntity<>(new HttpStatusResponse(count,HttpStatus.OK.value(),"Successfully Leave History count fetched"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.BAD_REQUEST.value(),"Error in getting Leave History count"),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * getting all employees leave applied using filtering method
     * @param empId
     * @param filterOption
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping("/all/{empId}")
    public ResponseEntity<HttpStatusResponse> filterLeaveApplied(@PathVariable Integer empId, @RequestBody FilterOption filterOption) throws BusinessServiceException {
        return ofNullable(leaveAppliedService.filterLeaveApplied(empId,filterOption)).filter(CollectionUtils::isNotEmpty)
                .map(leaveAppliedDTOS -> new ResponseEntity<>
                        (new HttpStatusResponse(leaveAppliedDTOS, HttpStatus.OK.value(), "Successfully Getting the employee leave applied"), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "No records found"), HttpStatus.NOT_FOUND));
    }

    /**
     * employee applying their leaves
     * @param empId
     * @param leaveAppliedDTO
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> empApplyingLeave(@PathVariable int empId,@RequestBody LeaveAppliedDTO leaveAppliedDTO) throws BusinessServiceException {
        LeaveAppliedDTO leave = leaveAppliedService.empApplyingLeave(empId,leaveAppliedDTO);
        if(leave!=null){
            return new ResponseEntity<>(new HttpStatusResponse(leave,HttpStatus.OK.value(),"Successfully Leave Applied"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.BAD_REQUEST.value(),"Error in Leave Applied from controller"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count/approved")
    public Long getApprovedLeaveCount(){
        return leaveAppliedService.getApprovedLeaveCount();
    }

    @GetMapping("/count/rejected")
    public Long getRejectedLeaveCount(){
        return leaveAppliedService.getRejectedLeaveCount();
    }

    @GetMapping("/count/requested")
    public Long getRequestedLeaveCount(){
        return leaveAppliedService.getRequestedLeaveCount();
    }

    @GetMapping("/approved")
    public ResponseEntity<HttpStatusResponse> getAllApprovedLeaves(){
        return new ResponseEntity<>(new HttpStatusResponse(leaveAppliedService.getAllApprovedLeaves(), HttpStatus.OK.value(),"Fetching all approved leaves"),HttpStatus.OK);
    }

    @GetMapping("/rejected")
    public ResponseEntity<HttpStatusResponse> getAllRejectedLeaves(){
        return new ResponseEntity<>(new HttpStatusResponse(leaveAppliedService.getAllRejectedLeaves(), HttpStatus.OK.value(),"Fetching all rejected leaves"),HttpStatus.OK);
    }

    @GetMapping("/requested")
    public ResponseEntity<HttpStatusResponse> getAllRequestedLeaves(){
        return new ResponseEntity<>(new HttpStatusResponse(leaveAppliedService.getAllRequestedLeaves(), HttpStatus.OK.value(),"Fetching all requested leaves"),HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getLeaveHistoryBYEmployee(@PathVariable int empId){
        return new ResponseEntity<>(new HttpStatusResponse(leaveAppliedService.getLeaveHistoryBYEmployee(empId),HttpStatus.OK.value(),""),HttpStatus.OK);
    }

    @PutMapping
    public void updateLeaveStatus(@RequestBody LeaveAppliedDTO leaveAppliedDTO){
        leaveAppliedService.updateLeaveStatus(leaveAppliedDTO);
    }


}
