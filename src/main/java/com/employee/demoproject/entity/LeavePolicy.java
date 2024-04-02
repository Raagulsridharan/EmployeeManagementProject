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

    public LeavePolicy() {
    }

    public LeavePolicy(int id, String leave_types) {
        this.id = id;
        this.leave_types = leave_types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeave_types() {
        return leave_types;
    }

    public void setLeave_types(String leave_types) {
        this.leave_types = leave_types;
    }
}
