package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "designations")
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    private String salary_package;

    @OneToMany(mappedBy = "designation")
    private List<EmpRoleSalary> empRoleSalary;

    public Designation() {
    }

    public Designation(int id, String role, String salary_package) {
        this.id = id;
        this.role = role;
        this.salary_package = salary_package;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
