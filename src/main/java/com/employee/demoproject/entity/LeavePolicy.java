package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "leave_policy")
public class LeavePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String leave_types;

    @OneToMany(mappedBy = "leavePolicy")
    private List<LeaveApplied> leaveAppliedList;
}
