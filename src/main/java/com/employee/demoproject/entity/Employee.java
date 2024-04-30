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
    private Date birthday;
    private String gender;
    private long mobile;
    private String email;
    private String address;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
    private String status;

    @OneToMany(mappedBy = "employee_role_salary",cascade = CascadeType.ALL)
    private List<EmpRoleSalary> empRoleSalary;

    @OneToMany(mappedBy = "employee_has_leave",cascade = CascadeType.ALL)
    private List<EmployeeHasLeave> employeeHasLeaveList;

    @OneToMany(mappedBy = "employee_leave_applied",cascade = CascadeType.ALL)
    private List<LeaveApplied> leaveAppliedList;

    @OneToMany(mappedBy = "employee_login",cascade = CascadeType.ALL)
    private List<LoginDetails> loginDetails;

    public Employee() {}

    public Employee(int id, String name, Date birthday, String gender, long mobile, String email, String address, Department department, String status) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.department = department;
        this.status = status;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
