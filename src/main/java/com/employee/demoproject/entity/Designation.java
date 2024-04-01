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

    private String Salary_package;

    @OneToMany(mappedBy = "designation")
    private List<EmpRoleSalary> empRoleSalary;
}
