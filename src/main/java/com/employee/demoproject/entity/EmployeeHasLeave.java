package com.employee.demoproject.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "emp_has_leave",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"employee", "leave_id"})})
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

    private Integer no_of_days;
    private Date updated_on;

    public EmployeeHasLeave() {
    }

    public EmployeeHasLeave(int id, Employee employee_has_leave, LeavePolicy leavePolicy, Integer no_of_days, Date updated_on) {
        this.id = id;
        this.employee_has_leave = employee_has_leave;
        this.leavePolicy = leavePolicy;
        this.no_of_days = no_of_days;
        this.updated_on = updated_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee_has_leave() {
        return employee_has_leave;
    }

    public void setEmployee_has_leave(Employee employee_has_leave) {
        this.employee_has_leave = employee_has_leave;
    }

    public LeavePolicy getLeavePolicy() {
        return leavePolicy;
    }

    public void setLeavePolicy(LeavePolicy leavePolicy) {
        this.leavePolicy = leavePolicy;
    }

    public Integer getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(Integer no_of_days) {
        this.no_of_days = no_of_days;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }
}
