package com.employee.demoproject.dto;

import java.sql.Date;

public class EmployeeHasLeaveDTO {
    private int id;
    private String empName;
    private String leaveType;
    private int noOfDays;
    private Date activatedOn;

    public EmployeeHasLeaveDTO() {
    }
    public EmployeeHasLeaveDTO(int id, String empName, String leaveType, int noOfDays, Date activatedOn) {
        this.id = id;
        this.empName = empName;
        this.leaveType = leaveType;
        this.noOfDays = noOfDays;
        this.activatedOn = activatedOn;
    }
    public EmployeeHasLeaveDTO(String empName, String leaveType, int noOfDays) {
        this.empName = empName;
        this.leaveType = leaveType;
        this.noOfDays = noOfDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Date getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(Date activatedOn) {
        this.activatedOn = activatedOn;
    }
}
