package com.employee.demoproject.dto;


public class LoginDetailsDTO {
    private String username;
    private String password;

    public LoginDetailsDTO(){}

    public LoginDetailsDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUserName(String username){
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
}
