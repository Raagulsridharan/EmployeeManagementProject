package com.employee.demoproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DesignationDTO {

    @NotBlank(message = "Role is required")
    @Size(max = 255, message = "Role must be less than 255 characters")
    private String role;

    @NotNull(message = "Salary package is required")
    @NotBlank(message = "Salary package cannot be blank")
    private String salary_package;

    public DesignationDTO(){}

    public DesignationDTO(String role,String salaryPackage){
        this.role = role;
        this.salary_package = salaryPackage;
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
}
