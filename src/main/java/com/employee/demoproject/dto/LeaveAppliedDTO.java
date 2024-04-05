package com.employee.demoproject.dto;

import java.sql.Date;

public class LeaveAppliedDTO {
    private Integer id;
    private Integer empId;
    private Integer leaveType;
    private String note;
    private Long noOfDays;
    private Date fromDate;
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

    public Long getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Long noOfDays) {
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
