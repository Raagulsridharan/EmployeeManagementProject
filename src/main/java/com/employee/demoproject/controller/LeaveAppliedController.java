package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.HttpStatusEntity;
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
    public ResponseEntity<HttpStatusEntity> getAllApprovedLeaves(){
        return ResponseEntity.ok(new HttpStatusEntity(leaveAppliedService.getAllApprovedLeaves(), HttpStatus.OK.value(),"Fetching all approved leaves"));
    }

    @GetMapping("/getAllRejectedLeaves")
    public ResponseEntity<HttpStatusEntity> getAllRejectedLeaves(){
        return ResponseEntity.ok(new HttpStatusEntity(leaveAppliedService.getAllRejectedLeaves(), HttpStatus.OK.value(),"Fetching all rejected leaves"));
    }

    @GetMapping("/getAllRequestedLeaves")
    public ResponseEntity<HttpStatusEntity> getAllRequestedLeaves(){
        return ResponseEntity.ok(new HttpStatusEntity(leaveAppliedService.getAllRequestedLeaves(), HttpStatus.OK.value(),"Fetching all requested leaves"));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<List<LeaveApplied>> getLeaveHistoryBYEmployee(@PathVariable int empId){
        return new ResponseEntity<>(leaveAppliedService.getLeaveHistoryBYEmployee(empId),HttpStatus.OK);
    }

    @PostMapping("/employeeApplyingLeave/{empId}")
    public String empApplyingLeave(@PathVariable int empId,@RequestBody LeaveAppliedDTO leaveAppliedDTO) {
        return leaveAppliedService.empApplyingLeave(empId,leaveAppliedDTO);
    }

    @PutMapping
    public void updateLeaveStatus(@RequestBody LeaveAppliedDTO leaveAppliedDTO){
        leaveAppliedService.updateLeaveStatus(leaveAppliedDTO);
        //return "Your Leave "+leaveAppliedDTO.getStatus();
    }
}
