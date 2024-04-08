package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empLogin")
public class LoginDetailsController {
    @Autowired
    private LoginDetailsService loginDetailsService;

    @PutMapping("/updatePassword/{id}")
    public String updatePassword(@PathVariable int id, @RequestBody String password){
        loginDetailsService.updatePassword(id,password);
        return "Password updated...!!!";
    }

    @GetMapping
    public Integer employeeLogin(@RequestBody LoginDetailsDTO loginDetailsDTO) {
        return loginDetailsService.employeeLogin(loginDetailsDTO);
    }

    @PutMapping
    public String activatingAccount(@RequestBody LoginDetailsDTO loginDetailsDTO) {
        loginDetailsService.activatingAccount(loginDetailsDTO);
        return "Account activated successfully";
    }
}
