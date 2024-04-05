package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.service.LeaveAppliedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveApplied")
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
    public ResponseEntity<List<LeaveApplied>> getAllApprovedLeaves(){
        return new ResponseEntity<>(leaveAppliedService.getAllApprovedLeaves(), HttpStatus.OK);
    }

    @GetMapping("/getAllRejectedLeaves")
    public ResponseEntity<List<LeaveApplied>> getAllRejectedLeaves(){
        return new ResponseEntity<>(leaveAppliedService.getAllRejectedLeaves(), HttpStatus.OK);
    }

    @GetMapping("/getAllRequestedLeaves")
    public ResponseEntity<List<LeaveApplied>> getAllRequestedLeaves(){
        return new ResponseEntity<>(leaveAppliedService.getAllRequestedLeaves(), HttpStatus.OK);
    }

    @GetMapping("employeeLeaveHistory/{empId}")
    public ResponseEntity<List<LeaveApplied>> getLeaveHistoryBYEmployee(@PathVariable int empId){
        return new ResponseEntity<>(leaveAppliedService.getLeaveHistoryBYEmployee(empId),HttpStatus.OK);
    }

    @PostMapping("/employeeApplyingLeave/{empId}")
    public String empApplyingLeave(@PathVariable int empId,@RequestBody LeaveAppliedDTO leaveAppliedDTO) {
        return leaveAppliedService.empApplyingLeave(empId,leaveAppliedDTO);
    }

    @PutMapping("/updateLeaveStatus")
    public String updateLeaveStatus(@RequestBody LeaveAppliedDTO leaveAppliedDTO){
        leaveAppliedService.updateLeaveStatus(leaveAppliedDTO);
        return "Your Leave "+leaveAppliedDTO.getStatus();
    }
}
