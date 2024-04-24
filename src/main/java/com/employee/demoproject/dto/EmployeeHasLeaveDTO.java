package com.employee.demoproject.dto;

import java.sql.Date;

public class EmployeeHasLeaveDTO {

    private Integer leaveId;
    private String leaveType;
    private Integer noOfdays;
    private Date updatedOn;

    public EmployeeHasLeaveDTO() {
    }

    public EmployeeHasLeaveDTO(Integer leaveId, String leaveType, Integer noOfdays, Date updatedOn) {
        this.leaveId = leaveId;
        this.leaveType = leaveType;
        this.noOfdays = noOfdays;
        this.updatedOn = updatedOn;
    }

    public EmployeeHasLeaveDTO(Integer leaveId, Integer noOfdays) {
        this.leaveId = leaveId;
        this.noOfdays = noOfdays;
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

    public Integer getNoOfdays() {
        return noOfdays;
    }

    public void setNoOfdays(Integer noOfdays) {
        this.noOfdays = noOfdays;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }


}
