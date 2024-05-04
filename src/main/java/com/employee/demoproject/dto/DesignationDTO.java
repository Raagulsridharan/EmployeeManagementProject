package com.employee.demoproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DesignationDTO {
    private Integer id;

    @NotBlank(message = "Role is required")
    @Size(max = 255, message = "Role must be less than 255 characters")
    private String role;

    @NotNull(message = "Salary package is required")
    @NotBlank(message = "Salary package cannot be blank")
    private String salary_package;

    @NotNull(message = "Department id is required")
    private Integer departmentId;

    public DesignationDTO(){}

    public DesignationDTO(Integer id, String role,String salaryPackage, Integer departmentId){
        this.id = id;
        this.role = role;
        this.salary_package = salaryPackage;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalary_package() {
        return salary_package;
    }

    public void setSalary_package(String salary_package) {
        this.salary_package = salary_package;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
