package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private long mobile;
    private String email;
    private String address;
    private String password;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
    private Date activated_on;

    @OneToMany(mappedBy = "employee_role_salary")
    private List<EmpRoleSalary> empRoleSalary;

    @OneToMany(mappedBy = "employee_has_leave")
    private List<EmployeeHasLeave> employeeHasLeaveList;

    @OneToMany(mappedBy = "employee_leave_applied")
    private List<LeaveApplied> leaveAppliedList;

    public Employee() {}
    public Employee(int id, String name, String gender, long mobile, String email, String address, Department department, Date activated_on) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.password = password;
        this.department = department;
        this.activated_on = activated_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getActivated_on() {
        return activated_on;
    }

    public void setActivated_on(Date activated_on) {
        this.activated_on = activated_on;
    }
}
