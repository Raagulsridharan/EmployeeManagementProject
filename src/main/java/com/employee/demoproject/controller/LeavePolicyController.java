package com.employee.demoproject.controller;

import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.HttpStatusEntity;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.LeavePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BaseAPI.LEAVE_POLICY)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeavePolicyController {

    @Autowired
    private LeavePolicyService leavePolicyService;

    @GetMapping("/createLeavePolicy")
    public String createLeavePolicy(@RequestBody LeavePolicy leavePolicy){
        leavePolicyService.createLeavePolicy(leavePolicy);
        return "Successfully leavePolicy created...!";
    }

    @GetMapping
    public ResponseEntity<HttpStatusEntity> getAllLeavePolicy(){
        return ResponseEntity.ok(new HttpStatusEntity(leavePolicyService.getAllLeavePolicy(), HttpStatus.OK.value(), "Fetching All leave types"));
    }

    @GetMapping("/getLeavePolicyById/{id}")
    public LeavePolicy getLeavePolicyById(@PathVariable int id){
        return leavePolicyService.getLeavePolicyById(id);
    }

    @GetMapping("/getLeavePolicyByName/{name}")
    public LeavePolicy getLeavePolicyByName(@PathVariable String name){
        return leavePolicyService.getLeavePolicyByName(name);
    }

    @GetMapping("/getLeaveTypesCount")
    public Long getLeaveTypesCount(){
        return leavePolicyService.getLeaveTypesCount();
    }
}
