package com.employee.demoproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PayrollDTO {
    private Integer id;

    @NotNull(message = "Employee ID cannot be null")
    private Integer empId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private Double net_sal_month;
    private String month;

    @PositiveOrZero(message = "Paid salary must be positive or zero")
    private Double paid_salary;

    private String description;
    private String status;

    public PayrollDTO() {
    }

    public PayrollDTO(Integer id, Integer empId, String name, Double net_sal_month, String month, Double paid_salary, String description, String status) {
        this.id = id;
        this.empId = empId;
        this.name = name;
        this.net_sal_month = net_sal_month;
        this.month = month;
        this.paid_salary = paid_salary;
        this.description = description;
        this.status = status;
    }

    public PayrollDTO(String month, String description) {
        this.month = month;
        this.description = description;
    }

    public PayrollDTO(Integer id, String description){
        this.id = id;
        this.description = description;
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

    public Double getNet_sal_month() {
        return net_sal_month;
    }

    public void setNet_sal_month(Double net_sal_month) {
        this.net_sal_month = net_sal_month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getPaid_salary() {
        return paid_salary;
    }

    public void setPaid_salary(Double paid_salary) {
        this.paid_salary = paid_salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
