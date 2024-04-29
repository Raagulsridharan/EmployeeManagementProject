package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.service.LeaveAppliedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveApplied")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeaveAppliedController {

    @Autowired
    private LeaveAppliedService leaveAppliedService;

    @GetMapping("/getApprovedLeaveCount")
    public Long getApprovedLeaveCount(){
        return leaveAppliedService.getApprovedLeaveCount();
    }

    @GetMapping("/getRejectedLeaveCount")
    public Long getRejectedLeaveCount(){
        return leaveAppliedService.getRejectedLeaveCount();
    }

    @GetMapping("/getRequestedLeaveCount")
    public Long getRequestedLeaveCount(){
        return leaveAppliedService.getRequestedLeaveCount();
    }

    @GetMapping("/getAllApprovedLeaves")
    public ResponseEntity<HttpStatusResponse> getAllApprovedLeaves(){
        return ResponseEntity.ok(new HttpStatusResponse(leaveAppliedService.getAllApprovedLeaves(), HttpStatus.OK.value(),"Fetching all approved leaves"));
    }

    @GetMapping("/getAllRejectedLeaves")
    public ResponseEntity<HttpStatusResponse> getAllRejectedLeaves(){
        return ResponseEntity.ok(new HttpStatusResponse(leaveAppliedService.getAllRejectedLeaves(), HttpStatus.OK.value(),"Fetching all rejected leaves"));
    }

    @GetMapping("/getAllRequestedLeaves")
    public ResponseEntity<HttpStatusResponse> getAllRequestedLeaves(){
        return ResponseEntity.ok(new HttpStatusResponse(leaveAppliedService.getAllRequestedLeaves(), HttpStatus.OK.value(),"Fetching all requested leaves"));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> getLeaveHistoryBYEmployee(@PathVariable int empId){
        return new ResponseEntity<>(new HttpStatusResponse(leaveAppliedService.getLeaveHistoryBYEmployee(empId),HttpStatus.OK.value(),""),HttpStatus.OK);
    }

    @PostMapping("/{empId}")
    public ResponseEntity<HttpStatusResponse> empApplyingLeave(@PathVariable int empId,@RequestBody LeaveAppliedDTO leaveAppliedDTO) throws BusinessServiceException {
        LeaveAppliedDTO leave = leaveAppliedService.empApplyingLeave(empId,leaveAppliedDTO);
        if(leave!=null){
            return new ResponseEntity<>(new HttpStatusResponse(leave,HttpStatus.OK.value(),"Successfully Leave Applied"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.BAD_REQUEST.value(),"Error in Leave Applied from controller"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public void updateLeaveStatus(@RequestBody LeaveAppliedDTO leaveAppliedDTO){
        leaveAppliedService.updateLeaveStatus(leaveAppliedDTO);
        //return "Your Leave "+leaveAppliedDTO.getStatus();
    }

    @GetMapping("/count/{empId}")
    public ResponseEntity<HttpStatusResponse> getEmployeeLeaveHistoryCount(@PathVariable int empId) throws BusinessServiceException{
        Long count = leaveAppliedService.getEmployeeLeaveHistoryCount(empId);
        if(count!=null){
            return new ResponseEntity<>(new HttpStatusResponse(count,HttpStatus.OK.value(),"Successfully Leave History count fetched"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.BAD_REQUEST.value(),"Error in getting Leave History count"),HttpStatus.BAD_REQUEST);
        }
    }
}
