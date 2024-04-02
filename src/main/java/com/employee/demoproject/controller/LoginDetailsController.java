package com.employee.demoproject.controller;

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
}
