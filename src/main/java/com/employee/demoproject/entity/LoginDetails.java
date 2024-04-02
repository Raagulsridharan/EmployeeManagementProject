package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "login_details")
public class LoginDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private int flag;
    private Date activated_on;
    @ManyToOne
    @JoinColumn(name = "emp_i_d")
    private Employee employee_login;

    public LoginDetails() {
    }

    public LoginDetails(int id, String username, String password, int flag, Date activated_on, Employee employee_login) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.activated_on = activated_on;
        this.employee_login = employee_login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getActivated_on() {
        return activated_on;
    }

    public void setActivated_on(Date activated_on) {
        this.activated_on = activated_on;
    }

    public Employee getEmployee_login() {
        return employee_login;
    }

    public void setEmployee_login(Employee employee_login) {
        this.employee_login = employee_login;
    }

    @Override
    public String toString() {
        return "LoginDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                ", activated_on=" + activated_on +
                ", employee_login=" + employee_login +
                '}';
    }
}
