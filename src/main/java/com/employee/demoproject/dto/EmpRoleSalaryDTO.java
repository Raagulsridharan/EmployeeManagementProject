package com.employee.demoproject.dto;

public class EmpRoleSalaryDTO {
    private int id;
    private String emp_name;
    private Integer departmentId;
    private String department;
    private String role;
    private String annual_package;

    private int roleId;
    private Double salaryPack;

    private Long totalCount;

    public EmpRoleSalaryDTO(){}

    public EmpRoleSalaryDTO(int id, String emp_name, Integer departmentId, String department, String role, String annual_package, Long totalCount) {
        this.id = id;
        this.emp_name = emp_name;
        this.departmentId = departmentId;
        this.department = department;
        this.role = role;
        this.annual_package = annual_package;
        this.totalCount = totalCount;
    }

    public EmpRoleSalaryDTO(int roleId, Double salaryPack){
        this.roleId = roleId;
        this.salaryPack = salaryPack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public String getAnnual_package() {
        return annual_package;
    }

    public void setAnnual_package(String annual_package) {
        this.annual_package = annual_package;
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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
