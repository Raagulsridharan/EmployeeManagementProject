package com.employee.demoproject.dto;

public class LeavePolicyDTO {
    private Integer id;
    private String leaveType;

    public LeavePolicyDTO(){}

    public LeavePolicyDTO(Integer id, String leaveType){
        this.id = id;
        this.leaveType = leaveType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
}
