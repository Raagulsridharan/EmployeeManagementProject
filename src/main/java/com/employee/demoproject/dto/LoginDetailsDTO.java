package com.employee.demoproject.dto;


public class LoginDetailsDTO {
    private Integer empId;
    private String username;
    private String password;
    private String newPassword;
    private Integer flag;

    public LoginDetailsDTO(){}

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
