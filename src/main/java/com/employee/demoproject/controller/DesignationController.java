package com.employee.demoproject.controller;

import com.employee.demoproject.endPoints.BaseAPI;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.responce.HttpStatusResponse;
import com.employee.demoproject.service.DesignationService;
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
     * @param designation
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatusResponse> createDesignation(@RequestBody Designation designation){
        designationService.createDesignation(designation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new HttpStatusResponse(designation, HttpStatus.CREATED.value(),"Designation successfully created"));    }

    /**
     * update/alter the designation or salary_package with respect to the corresponding id
     * using updated designation object
     * @param id
     * @param designation
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatusResponse> updateDesignation(@PathVariable int id, @RequestBody Designation designation){
        designationService.updateDesignation(id,designation);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new HttpStatusResponse(designationService.getDesignationById(id), HttpStatus.OK.value(),"Designation updated"));
    }

    /**
     * retrieving all the designations and salary package
     * @return
     */
    @GetMapping
    public ResponseEntity<HttpStatusResponse> getAllDesignation(){
        List<Designation> designationList = designationService.getAllDesignation();
        if(designationList!=null){
            return ResponseEntity.ok(new HttpStatusResponse(designationList,HttpStatus.OK.value(),"Designation retrieved Successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"Not found"));
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
            return ResponseEntity.ok(new HttpStatusResponse(designation,HttpStatus.OK.value(),"Department retrieved Successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HttpStatusResponse(null,HttpStatus.NOT_FOUND.value(),"Department Not found"));
        }    }

    /**
     * getting designations count
     * @return
     */
    @GetMapping("/getDesignationCount")
    public Long getDesignationCount(){
        return designationService.getDesignationCount();
    }

//    @GetMapping("/{role}")
//    public Designation getDesignationByRole(@PathVariable String role){
//        return designationService.getDesignationByRole(role);
//    }
//
    @GetMapping("/getUserType/{email}")
    public ResponseEntity<HttpStatusResponse> getDesignationByEmail(@PathVariable String email){
        return ResponseEntity.ok(new HttpStatusResponse(designationService.getDesignationByEmail(email),HttpStatus.OK.value(),"Getting user type"));
    }
}
