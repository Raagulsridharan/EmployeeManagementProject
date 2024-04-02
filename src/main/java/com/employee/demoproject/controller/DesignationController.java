package com.employee.demoproject.controller;

import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designations")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @PostMapping("/saveDesignation")
    public String createDesignation(@RequestBody Designation designation){
        designationService.createDesignation(designation);
        return "Designation saved...!";
    }

    @PutMapping("/updateDesignation/{id}")
    public String updateDesignation(@PathVariable int id, @RequestBody Designation designation){
        designationService.updateDesignation(id,designation);
        return "Designation updated...!!!";
    }

    @GetMapping("/getAllDesignation")
    public ResponseEntity getAllDesignation(){
        return new ResponseEntity<>(designationService.getAllDesignation(), HttpStatus.OK);
    }

    @GetMapping("/getDesigById/{id}")
    public Designation getDesignationById(@PathVariable int id){
        return designationService.getDesignationById(id);
    }

    @GetMapping("/getDesigByRole/{role}")
    public Designation getDesignationByName(@PathVariable String role){
        return designationService.getDesignationByName(role);
    }

    @GetMapping("/getDesignationCount")
    public Long getDesignation(){
        return designationService.getDesignationCount();
    }
}
