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

    private double annual_salary_pack;
    private double basic_sal_month;
    private double tax_reduction_month;
    private double net_sal_month;

    @OneToMany(mappedBy = "empRoleSalary_payroll",cascade = CascadeType.ALL)
    private List<Payroll> payroll;

    public EmpRoleSalary() {
    }

    public EmpRoleSalary(int id, Employee employee_role_salary, Designation designation, double annual_salary_pack, double basic_sal_month, double tax_reduction_month, double net_sal_month) {
        this.id = id;
        this.employee_role_salary = employee_role_salary;
        this.designation = designation;
        this.annual_salary_pack = annual_salary_pack;
        this.basic_sal_month = basic_sal_month;
        this.tax_reduction_month = tax_reduction_month;
        this.net_sal_month = net_sal_month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee_role_salary() {
        return employee_role_salary;
    }

    public void setEmployee_role_salary(Employee employee_role_salary) {
        this.employee_role_salary = employee_role_salary;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public double getAnnual_salary_pack() {
        return annual_salary_pack;
    }

    public void setAnnual_salary_pack(double annual_salary_pack) {
        this.annual_salary_pack = annual_salary_pack;
    }

    public double getBasic_sal_month() {
        return basic_sal_month;
    }

    public void setBasic_sal_month(double basic_sal_month) {
        this.basic_sal_month = basic_sal_month;
    }

    public double getTax_reduction_month() {
        return tax_reduction_month;
    }

    public void setTax_reduction_month(double tax_reduction_month) {
        this.tax_reduction_month = tax_reduction_month;
    }

    public double getNet_sal_month() {
        return net_sal_month;
    }

    public void setNet_sal_month(double net_sal_month) {
        this.net_sal_month = net_sal_month;
    }
}
