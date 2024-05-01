package com.employee.demoproject.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class LoginDetailsDTO {
    private Integer id;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 30, message = "Password must be between 6 and 30 characters")
    private String password;

    private Integer flag;
    private Date activatedOn;

    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Integer empId;

    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be positive")
    private Integer deptId;
    public LoginDetailsDTO(){}

    public LoginDetailsDTO(Integer id, String username, String password, Integer flag, Date activatedOn, Integer empId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.activatedOn = activatedOn;
        this.empId = empId;
    }

    public LoginDetailsDTO(String username, String password, Integer deptId){
        this.username = username;
        this.password = password;
        this.deptId = deptId;
    }

    public LoginDetailsDTO(Integer empId,String password){
        this.empId = empId;
        this.password = password;
    }

    public LoginDetailsDTO(Integer empId, Integer flag){
        this.empId = empId;
        this.flag = flag;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(Date activatedOn) {
        this.activatedOn = activatedOn;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
