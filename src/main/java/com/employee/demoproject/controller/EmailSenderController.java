package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send")
    public ResponseEntity<Object> sendEmail(@RequestBody LoginDetailsDTO emailRequest){
        emailSenderService.sendEmail(emailRequest.getUsername(),emailRequest.getPassword());
        System.out.println("Email send success fully !");
        return ResponseEntity.ok().body("{\"message\": \"Email send success fully !\"}");
    }
}
