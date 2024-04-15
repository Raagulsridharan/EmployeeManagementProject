package com.employee.demoproject.dto;


import java.sql.Date;

public class LoginDetailsDTO {
    private Integer id;
    private String username;
    private String password;
    private Integer flag;
    private Date activatedOn;
    private Integer empId;

    public LoginDetailsDTO(){}

    public LoginDetailsDTO(Integer id, String username, String password, Integer flag, Date activatedOn, Integer empId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.activatedOn = activatedOn;
        this.empId = empId;
    }

    public LoginDetailsDTO(String username, String password){
        this.username = username;
        this.password = password;
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
}
