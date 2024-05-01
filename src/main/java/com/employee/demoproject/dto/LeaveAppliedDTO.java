package com.employee.demoproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class LeaveAppliedDTO {
    private Integer id;

    @NotNull(message = "Employee ID is required")
    private Integer empId;

    @NotNull(message = "Leave type is required")
    private Integer leaveType;

    @Size(max = 255, message = "Note must be less than 255 characters")
    private String note;

    @NotNull(message = "Number of days is required")
    @Positive(message = "Number of days must be positive")
    private Integer noOfDays;

    @NotNull(message = "From date is required")
    private Date fromDate;

    @NotNull(message = "To date is required")
    private Date toDate;

    private String status;
    private Date submittedOn;

    public LeaveAppliedDTO(){}

    public LeaveAppliedDTO(Integer leaveType, String note, Date fromDate, Date toDate){
        this.leaveType = leaveType;
        this.note = note;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LeaveAppliedDTO(Integer empId, Integer leaveType, Date fromDate, Date toDate, String status){
        this.empId = empId;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public LeaveAppliedDTO(Integer id, Integer empId, Integer leaveType, String status){
        this.id = id;
        this.empId = empId;
        this.leaveType = leaveType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }
}
