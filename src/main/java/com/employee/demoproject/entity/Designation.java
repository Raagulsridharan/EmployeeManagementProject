package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "designations",uniqueConstraints = {@UniqueConstraint(columnNames = {"role", "departmentId"})})
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String salary_package;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    @OneToMany(mappedBy = "designation")
    private List<EmpRoleSalary> empRoleSalary;

    public Designation() {
    }

    public Designation(Integer id, String role, String salary_package, Department department) {
        this.id = id;
        this.role = role;
        this.salary_package = salary_package;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
