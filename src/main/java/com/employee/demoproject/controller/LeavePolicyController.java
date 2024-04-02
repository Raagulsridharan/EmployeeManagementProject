package com.employee.demoproject.controller;

import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.service.LeavePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leavePolicy")
public class LeavePolicyController {

    @Autowired
    private LeavePolicyService leavePolicyService;

    @GetMapping("/createLeavePolicy")
    public String createLeavePolicy(@RequestBody LeavePolicy leavePolicy){
        leavePolicyService.createLeavePolicy(leavePolicy);
        return "Successfully leavePolicy created...!";
    }

    @GetMapping("/getAllLeavePolicy")
    public List<LeavePolicy> getAllLeavePolicy(){
        return leavePolicyService.getAllLeavePolicy();
    }

    @GetMapping("/getLeavePolicyById/{id}")
    public LeavePolicy getLeavePolicyById(@PathVariable int id){
        return leavePolicyService.getLeavePolicyById(id);
    }

    @GetMapping("/getLeavePolicyByName/{name}")
    public LeavePolicy getLeavePolicyByName(@PathVariable String name){
        return leavePolicyService.getLeavePolicyByName(name);
    }
}
