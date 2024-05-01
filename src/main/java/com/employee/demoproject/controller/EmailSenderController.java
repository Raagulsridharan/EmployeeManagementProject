package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseAPI.EMAIL)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailSenderService;

    /**
     * send mail get's details of username and password, then it's processing to send the mail
     * @param emailRequest
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> sendEmail(@RequestBody LoginDetailsDTO emailRequest) throws BusinessServiceException {
        emailSenderService.sendEmail(emailRequest.getUsername(),emailRequest.getPassword(),emailRequest.getDeptId());
        System.out.println("Email sent successfully...!");
        return new ResponseEntity<>(new HttpStatusResponse(emailRequest, HttpStatus.OK.value(), "Email sent successfully"),HttpStatus.OK);
    }
}
