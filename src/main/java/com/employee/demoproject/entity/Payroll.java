package com.employee.demoproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String month;
    private double paid_salary;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "salary_id")
    private EmpRoleSalary empRoleSalary_payroll;

    public Payroll() {
    }

    public Payroll(int id, String month, double paid_salary, String description, String status, EmpRoleSalary empRoleSalary_payroll) {
        this.id = id;
        this.month = month;
        this.paid_salary = paid_salary;
        this.description = description;
        this.status = status;
        this.empRoleSalary_payroll = empRoleSalary_payroll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getPaid_salary() {
        return paid_salary;
    }

    public void setPaid_salary(double paid_salary) {
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

    public EmpRoleSalary getEmpRoleSalary_payroll() {
        return empRoleSalary_payroll;
    }

    public void setEmpRoleSalary_payroll(EmpRoleSalary empRoleSalary_payroll) {
        this.empRoleSalary_payroll = empRoleSalary_payroll;
    }
}
