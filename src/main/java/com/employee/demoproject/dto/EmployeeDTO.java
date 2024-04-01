package com.employee.demoproject.dto;

import java.sql.Date;

public class EmployeeDTO {
    private int id;
    private String emp_name;
    private String gender;
    private Long mobile;
    private String email;
    private String address;
    private String department;
    private Date activated_on;

    public EmployeeDTO() {}
    public EmployeeDTO(Integer id, String emp_name, String gender, Long mobile, String email, String address, String department, Date activated_on) {
        this.id = id;
        this.emp_name = emp_name;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.department = department;
        this.activated_on = activated_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String name) {
        this.emp_name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getActivated_on() {
        return activated_on;
    }

    public void setActivated_on(Date activated_on) {
        this.activated_on = activated_on;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + emp_name + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}