package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "leave_applied")
public class LeaveApplied {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee_leave_applied;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeavePolicy leavePolicy;

    private String note;
    private int no_of_days;
    private Date from_date;
    private Date to_date;
    private String status;
    private Date submitted_on;
}
