package com.employee.demoproject.controller;

import com.employee.demoproject.dto.DesignationDTO;
import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.exceptions.BusinessServiceException;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.DesignationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BaseAPI.DESIGNATION)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    /**
     * create new designation with salary package
     * @param designationDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> createDesignation(@RequestBody @Valid DesignationDTO designationDTO) throws BusinessServiceException {
        DesignationDTO saved = designationService.createDesignation(designationDTO);
        if(saved!=null){
            return new ResponseEntity<>(new HttpStatusResponse(saved, HttpStatus.CREATED.value(),"Designation successfully created"),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.BAD_REQUEST.value(),"Designation successfully created"),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * update/alter the designation or salary_package with respect to the corresponding id
     * using updated designation object
     * @param id
     * @param designationDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> updateDesignation(@PathVariable int id, @RequestBody @Valid DesignationDTO designationDTO) throws BusinessServiceException{
        DesignationDTO updated = designationService.updateDesignation(id,designationDTO);
        if(updated!=null){
            return new ResponseEntity<>(new HttpStatusResponse(updated, HttpStatus.OK.value(),"Designation updated"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpStatusResponse(null, HttpStatus.BAD_REQUEST.value(),"Designation updated"),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * retrieving all the designations and salary package
     * @return
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllDesignation(){
        List<Designation> designationList = designationService.getAllDesignation();
        if(designationList!=null){
            return new ResponseEntity<>(new HttpStatusResponse(designationList,HttpStatus.OK.value(),"Designation retrieved Successfully"),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"Not found"),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * retrieving single designation and it's salary package
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> getDesignationById(@PathVariable int id){
        Designation designation = designationService.getDesignationById(id);
        if(designation!=null){
            return new ResponseEntity<>(new HttpStatusResponse(designation,HttpStatus.OK.value(),"Department retrieved Successfully"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"Department Not found"),HttpStatus.NOT_FOUND);
        }    }

    /**
     * getting designations count
     * @return
     */
    @GetMapping("/count")
    public Long getDesignationCount(){
        return designationService.getDesignationCount();
    }

//    @GetMapping("/{role}")
//    public Designation getDesignationByRole(@PathVariable String role){
//        return designationService.getDesignationByRole(role);
//    }
//
    @GetMapping("/user-type/{email}")
    public ResponseEntity<HttpStatusResponse> getDesignationByEmail(@PathVariable String email){
        return new ResponseEntity<>(new HttpStatusResponse(designationService.getDesignationByEmail(email),HttpStatus.OK.value(),"Getting user type"),HttpStatus.OK);
    }
}
