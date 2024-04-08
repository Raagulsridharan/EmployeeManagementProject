package com.employee.demoproject.dto;

public class PayrollDTO {
    private int id;
    private String name;
    private Double net_sal_month;
    private String month;
    private Double paid_salary;
    private String description;
    private String status;

    public PayrollDTO() {
    }

    public PayrollDTO(int id, String name, Double net_sal_month, String month, Double paid_salary, String description, String status) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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