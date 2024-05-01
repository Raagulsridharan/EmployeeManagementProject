package com.employee.demoproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EmployeePaymentDTO {
    private Integer id;

    @NotNull(message = "Employee Id cannot be null")
    @Positive(message = "Employee Id must be positive")
    private Integer empId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Department cannot be blank")
    private String dept;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotNull(message = "Salary pack cannot be null")
    @Positive(message = "Salary pack must be positive")
    private Double salaryPack;

    @NotNull(message = "Basic salary per month cannot be null")
    @Positive(message = "Basic salary per month must be positive")
    private Double basic_sal_month;

    @NotNull(message = "Tax reduction per month cannot be null")
    @Positive(message = "Tax reduction per month must be positive")
    private Double tax_reduction_month;

    @NotNull(message = "Net salary per month cannot be null")
    @Positive(message = "Net salary per month must be positive")
    private Double net_sal_month;

    public EmployeePaymentDTO() {
    }

    public EmployeePaymentDTO(Integer id, Integer empId, String name, String dept, String role, Double salaryPack){
        this.id = id;
        this.empId = empId;
        this.name = name;
        this.dept = dept;
        this.role = role;
        this.salaryPack = salaryPack;
    }

    public EmployeePaymentDTO( Integer empId, String name, String dept, String role, Double salaryPack, Double basic_sal_month, Double tax_reduction_month, Double net_sal_month) {
        this.empId = empId;
        this.name = name;
        this.dept = dept;
        this.role = role;
        this.salaryPack = salaryPack;
        this.basic_sal_month = basic_sal_month;
        this.tax_reduction_month = tax_reduction_month;
        this.net_sal_month = net_sal_month;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getBasic_sal_month() {
        return basic_sal_month;
    }

    public void setBasic_sal_month(Double basic_sal_month) {
        this.basic_sal_month = basic_sal_month;
    }

    public Double getTax_reduction_month() {
        return tax_reduction_month;
    }

    public void setTax_reduction_month(Double tax_reduction_month) {
        this.tax_reduction_month = tax_reduction_month;
    }

    public Double getNet_sal_month() {
        return net_sal_month;
    }

    public void setNet_sal_month(Double net_sal_month) {
        this.net_sal_month = net_sal_month;
    }

    public Double getSalaryPack() {
        return salaryPack;
    }

    public void setSalaryPack(Double salaryPack) {
        this.salaryPack = salaryPack;
    }

    @Override
    public String toString() {
        return "EmployeePaymentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", role='" + role + '\'' +
                ", basic_sal_month=" + basic_sal_month +
                ", tax_reduction_month=" + tax_reduction_month +
                ", net_sal_month=" + net_sal_month +
                '}';
    }
}
