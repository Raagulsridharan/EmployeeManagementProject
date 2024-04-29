package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empLogin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginDetailsController {
    @Autowired
    private LoginDetailsService loginDetailsService;

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> updatePassword(@PathVariable int id, @RequestBody LoginDetailsDTO loginDetailsDTO){
        LoginDetailsDTO login = loginDetailsService.updatePassword(id,loginDetailsDTO);
        if(login!=null){
            return new ResponseEntity<>(new HttpStatusResponse(login, HttpStatus.OK.value(),"Password changed"),HttpStatus.OK);
        }else{
             return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "Getting department count"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public LoginDetailsDTO employeeLogin(@RequestBody LoginDetailsDTO loginDetailsDTO) {
        return loginDetailsService.employeeLogin(loginDetailsDTO);
    }

    @PutMapping
    public ResponseEntity<HttpStatusResponse> activatingAccount(@RequestBody LoginDetailsDTO loginDetailsDTO) {
        LoginDetailsDTO login = loginDetailsService.activatingAccount(loginDetailsDTO);
        if(login!=null){
            return new ResponseEntity<>(new HttpStatusResponse(login, HttpStatus.OK.value(),"Password changed"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "Getting department count"), HttpStatus.NOT_FOUND);
        }
        //System.out.println("Account activated successfully from controller");
    }
}
