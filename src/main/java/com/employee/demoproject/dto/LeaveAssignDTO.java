package com.employee.demoproject.dto;

import java.sql.Date;

public class LeaveAssignDTO {
    private Integer id;
    private String empName;
    private String department;
    private String role;
    private Date activatedOn;
    private Long totalCount;

    public LeaveAssignDTO() {
    }

    public LeaveAssignDTO(Integer id, String empName, String department, String role, Date activatedOn) {
        this.id = id;
        this.empName = empName;
        this.department = department;
        this.role = role;
        this.activatedOn = activatedOn;
    }

    public LeaveAssignDTO(Integer id, String empName, String department, String role, Date activatedOn, Long totalCount) {
        this.id = id;
        this.empName = empName;
        this.department = department;
        this.role = role;
        this.activatedOn = activatedOn;
        this.totalCount = totalCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(Date activatedOn) {
        this.activatedOn = activatedOn;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
