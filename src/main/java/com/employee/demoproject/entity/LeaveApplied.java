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
    private Long no_of_days;
    private Date from_date;
    private Date to_date;
    private String status;
    private Date submitted_on;

    public LeaveApplied() {
    }

    public LeaveApplied(int id, Employee employee_leave_applied, LeavePolicy leavePolicy, String note, Long no_of_days, Date from_date, Date to_date, String status, Date submitted_on) {
        this.id = id;
        this.employee_leave_applied = employee_leave_applied;
        this.leavePolicy = leavePolicy;
        this.note = note;
        this.no_of_days = no_of_days;
        this.from_date = from_date;
        this.to_date = to_date;
        this.status = status;
        this.submitted_on = submitted_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee_leave_applied() {
        return employee_leave_applied;
    }

    public void setEmployee_leave_applied(Employee employee_leave_applied) {
        this.employee_leave_applied = employee_leave_applied;
    }

    public LeavePolicy getLeavePolicy() {
        return leavePolicy;
    }

    public void setLeavePolicy(LeavePolicy leavePolicy) {
        this.leavePolicy = leavePolicy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(Long no_of_days) {
        this.no_of_days = no_of_days;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmitted_on() {
        return submitted_on;
    }

    public void setSubmitted_on(Date submitted_on) {
        this.submitted_on = submitted_on;
    }
}
