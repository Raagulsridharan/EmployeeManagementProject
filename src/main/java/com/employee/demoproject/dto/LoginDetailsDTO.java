package com.employee.demoproject.dto;


public class LoginDetailsDTO {
    private String username;
    private String password;
    private String newPassword;

    public LoginDetailsDTO(){}

    public LoginDetailsDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public LoginDetailsDTO(String username, String password, String newPassword){
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
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
}
