package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empLogin")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginDetailsController {
    @Autowired
    private LoginDetailsService loginDetailsService;

    @PutMapping("/updatePassword/{id}")
    public void updatePassword(@PathVariable int id, @RequestBody String password){
        loginDetailsService.updatePassword(id,password);
        //return "Password updated...!!!";
    }

    @PostMapping
    public LoginDetailsDTO employeeLogin(@RequestBody LoginDetailsDTO loginDetailsDTO) {
        return loginDetailsService.employeeLogin(loginDetailsDTO);
    }

    @PutMapping
    public void activatingAccount(@RequestBody LoginDetailsDTO loginDetailsDTO) {
        loginDetailsService.activatingAccount(loginDetailsDTO);
        System.out.println("Account activated successfully from controller");
    }
}
