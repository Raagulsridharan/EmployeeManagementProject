package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "emp_has_leave")
public class EmployeeHasLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee_has_leave;

    @ManyToOne
    @JoinColumn(name = "leave_id")
    private LeavePolicy leavePolicy;

    private int no_of_days;
    private Date updated_on;
}
