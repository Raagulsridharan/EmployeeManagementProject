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
}
