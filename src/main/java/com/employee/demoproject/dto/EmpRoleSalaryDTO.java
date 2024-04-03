package com.employee.demoproject.dto;

public class EmpRoleSalaryDTO {
    private int roleId;
    private Double salaryPack;

    public EmpRoleSalaryDTO(){}
    public EmpRoleSalaryDTO(int roleId,Double salaryPack){
        this.roleId = roleId;
        this.salaryPack = salaryPack;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Double getSalaryPack() {
        return salaryPack;
    }

    public void setSalaryPack(Double salaryPack) {
        this.salaryPack = salaryPack;
    }
}
