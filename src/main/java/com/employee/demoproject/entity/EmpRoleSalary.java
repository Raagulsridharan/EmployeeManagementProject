package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "emp_role_salary")
public class EmpRoleSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee_role_salary;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Designation designation;

    private int annual_salary_pack;
    private double basic_sal_month;
    private double tax_reduction_month;
    private double net_sal_month;

    @OneToMany(mappedBy = "empRoleSalary_payroll")
    private List<Payroll> payroll;
}
