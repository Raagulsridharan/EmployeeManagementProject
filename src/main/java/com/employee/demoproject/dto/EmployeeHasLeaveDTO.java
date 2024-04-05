package com.employee.demoproject.dto;

import java.sql.Date;

public class EmployeeHasLeaveDTO {
    private Integer id;
    private String empName;
    private String department;
    private String role;
    private Date activatedOn;

    private Integer leaveId;
    private String leaveType;
    private Long noOfDays;
    private Date updatedOn;

    public EmployeeHasLeaveDTO() {
    }

    public EmployeeHasLeaveDTO(Integer id, String empName,String department, String role, Date activatedOn) {
        this.id = id;
        this.empName = empName;
        this.department = department;
        this.role = role;
        this.activatedOn = activatedOn;
    }

    public EmployeeHasLeaveDTO(String leaveType, Long noOfDays,Date updatedOn) {
        this.leaveType = leaveType;
        this.noOfDays = noOfDays;
        this.updatedOn = updatedOn;
    }

    public EmployeeHasLeaveDTO(Integer leaveId, Long noOfDays) {
        this.leaveId = leaveId;
        this.noOfDays = noOfDays;
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

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Long getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Long noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }


}
