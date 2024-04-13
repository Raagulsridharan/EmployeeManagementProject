package com.employee.demoproject.controller;

import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @PostMapping
    public void createDesignation(@RequestBody Designation designation){
        designationService.createDesignation(designation);
        //return "Designation saved...!";
    }

    @PutMapping("/{id}")
    public String updateDesignation(@PathVariable int id, @RequestBody Designation designation){
        designationService.updateDesignation(id,designation);
        return "Designation updated...!!!";
    }

    @GetMapping
    public ResponseEntity getAllDesignation(){
        return new ResponseEntity<>(designationService.getAllDesignation(), HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public Designation getDesignationById(@PathVariable int id){
        return designationService.getDesignationById(id);
    }

    @GetMapping("/byRole/{role}")
    public Designation getDesignationByRole(@PathVariable String role){
        return designationService.getDesignationByRole(role);
    }

    @GetMapping("/getDesignationCount")
    public Long getDesignation(){
        return designationService.getDesignationCount();
    }

    @GetMapping("/{email}")
    public String getDesignationByEmail(@PathVariable String email){
        return designationService.getDesignationByEmail(email);
    }
}
