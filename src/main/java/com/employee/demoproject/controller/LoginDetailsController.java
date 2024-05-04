package com.employee.demoproject.controller;

import com.employee.demoproject.dto.LoginDetailsDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseAPI.LOGIN_DETAILS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginDetailsController {
    @Autowired
    private LoginDetailsService loginDetailsService;

    /**
     * updating password for existing user
     * @param id
     * @param loginDetailsDTO
     * @return
     * @throws BusinessServiceException
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> updatePassword(@PathVariable int id, @RequestBody LoginDetailsDTO loginDetailsDTO) throws BusinessServiceException {
        LoginDetailsDTO login = loginDetailsService.updatePassword(id,loginDetailsDTO);
        if(login!=null){
            return new ResponseEntity<>(new HttpStatusResponse(login, HttpStatus.OK.value(),"Password changed"),HttpStatus.OK);
        }else{
             return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "Getting department count"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * activating the account with updating the new password
     * @param loginDetailsDTO
     * @return
     * @throws BusinessServiceException
     */
    @PutMapping
    public ResponseEntity<HttpStatusResponse> activatingAccount(@RequestBody LoginDetailsDTO loginDetailsDTO) throws BusinessServiceException{
        LoginDetailsDTO login = loginDetailsService.activatingAccount(loginDetailsDTO);
        if(login!=null){
            return new ResponseEntity<>(new HttpStatusResponse(login, HttpStatus.OK.value(),"Password changed"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "Getting department count"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * employee login with the credentials
     * @param loginDetailsDTO
     * @return
     * @throws BusinessServiceException
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> employeeLogin(@RequestBody LoginDetailsDTO loginDetailsDTO) throws BusinessServiceException{
        LoginDetailsDTO loginDTO = loginDetailsService.employeeLogin(loginDetailsDTO);
        if(loginDTO!=null){
            return new ResponseEntity<>(new HttpStatusResponse(loginDTO,HttpStatus.OK.value(), "Login successfully"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.NOT_FOUND.value(), "Username or password not valid"),HttpStatus.NOT_FOUND);
        }
    }
}
