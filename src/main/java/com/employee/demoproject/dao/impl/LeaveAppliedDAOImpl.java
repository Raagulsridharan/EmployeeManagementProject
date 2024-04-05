package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.LeaveAppliedDAO;
import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.entity.LeavePolicy;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class LeaveAppliedDAOImpl implements LeaveAppliedDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long getApprovedLeaveCount() {
        Long approvedCount = (Long) sessionFactory.getCurrentSession()
                                            .createQuery("SELECT count(la) \n" +
                                                    "FROM LeaveApplied la \n" +
                                                    "where la.status = 'approved'")
                                            .uniqueResult();
        return approvedCount;
    }

    @Override
    public Long getRejectedLeaveCount() {
        Long rejectedCount = (Long) sessionFactory.getCurrentSession()
                .createQuery("SELECT count(la) \n" +
                        "FROM LeaveApplied la \n" +
                        "where la.status = 'rejected'")
                .uniqueResult();
        return rejectedCount;
    }

    @Override
    public Long getRequestedLeaveCount() {
        Long requestedCount = (Long) sessionFactory.getCurrentSession()
                .createQuery("SELECT count(la) \n" +
                        "FROM LeaveApplied la \n" +
                        "where la.status = 'requested'")
                .uniqueResult();
        return requestedCount;
    }

    @Override
    public List<LeaveApplied> getAllApprovedLeaves() {
        List<LeaveApplied> approvedCount = (List<LeaveApplied>) sessionFactory.getCurrentSession()
                .createQuery("SELECT la \n" +
                        "FROM LeaveApplied la \n" +
                        "where la.status = 'approved'")
                .getResultList();
        return approvedCount;
    }

    @Override
    public List<LeaveApplied> getAllRejectedLeaves() {
        List<LeaveApplied> rejectedList = (List<LeaveApplied>) sessionFactory.getCurrentSession()
                .createQuery("SELECT la \n" +
                        "FROM LeaveApplied la \n" +
                        "where la.status = 'Rejected'")
                .getResultList();
        return rejectedList;
    }

    @Override
    public List<LeaveApplied> getAllRequestedLeaves() {
        List<LeaveApplied> requestedList = (List<LeaveApplied>) sessionFactory.getCurrentSession()
                .createQuery("SELECT la \n" +
                        "FROM LeaveApplied la \n" +
                        "where la.status = 'Requested'")
                .getResultList();
        return requestedList;
    }

    @Override
    public List<LeaveApplied> getLeaveHistoryBYEmployee(int empId) {
        List<LeaveApplied> employeeLeaveHistory = (List<LeaveApplied>) sessionFactory.getCurrentSession()
                .createQuery("SELECT la \n" +
                        "FROM LeaveApplied la \n" +
                        "where la.employee_leave_applied.id = :empId")
                .setParameter("empId",empId)
                .getResultList();
        return employeeLeaveHistory;
    }

    @Override
    public String empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO) {
        LeaveApplied leaveApplied  = sessionFactory.getCurrentSession()
                .createQuery("SELECT l FROM LeaveApplied l WHERE l.status =:status AND l.employee_leave_applied.id = :empId AND :fromDate BETWEEN l.from_date AND l.to_date",LeaveApplied.class)
                .setParameter("status","requested")
                .setParameter("empId",empId)
                .setParameter("fromDate",leaveAppliedDTO.getFromDate())
                .uniqueResult();

        if(leaveApplied==null){
            Employee employee = sessionFactory.getCurrentSession().get(Employee.class,empId);
            LeavePolicy leavePolicy = sessionFactory.getCurrentSession().get(LeavePolicy.class,leaveAppliedDTO.getLeaveType());
            Long noOfDays = ChronoUnit.DAYS.between(leaveAppliedDTO.getFromDate().toLocalDate(),
                    leaveAppliedDTO.getToDate().toLocalDate());

            leaveApplied.setEmployee_leave_applied(employee);
            leaveApplied.setLeavePolicy(leavePolicy);
            leaveApplied.setNote(leaveAppliedDTO.getNote());
            leaveApplied.setFrom_date(leaveAppliedDTO.getFromDate());
            leaveApplied.setTo_date(leaveAppliedDTO.getToDate());
            leaveApplied.setNo_of_days(noOfDays+1);
            leaveApplied.setStatus("Requested");
            leaveApplied.setSubmitted_on(Date.valueOf(LocalDate.now()));

            sessionFactory.getCurrentSession().persist(leaveApplied);
            return "Successfully Leave requested...!!!";
        }else{
            return "Leave already requested. wait for approval";
        }
    }

    @Override
    public void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO) {
        LeaveApplied leaveApplied  = sessionFactory.getCurrentSession()
                .createQuery("SELECT l " +
                        "FROM LeaveApplied l " +
                        "WHERE l.status IN ('rejected','requested') " +
                        "AND l.employee_leave_applied.id = :empId " +
                        "AND l.leavePolicy.id = :leaveTypeId " +
                        "AND l.from_date = :fromDate " +
                        "AND l.to_date = :toDate" , LeaveApplied.class)
                .setParameter("empId",leaveAppliedDTO.getEmpId())
                .setParameter("leaveTypeId",leaveAppliedDTO.getLeaveType())
                .setParameter("fromDate",leaveAppliedDTO.getFromDate())
                .setParameter("toDate",leaveAppliedDTO.getToDate())
                .uniqueResult();

        leaveApplied.setStatus(leaveAppliedDTO.getStatus());
        sessionFactory.getCurrentSession().saveOrUpdate(leaveApplied);

        if(leaveAppliedDTO.getStatus().equals("Approved")){
            EmployeeHasLeave employeeHasLeave = sessionFactory.getCurrentSession()
                                                              .createQuery("select e \n" +
                                                                      "from EmployeeHasLeave e \n" +
                                                                      "where e.employee_has_leave.id = :empId \n" +
                                                                      "and e.leavePolicy.id = :leaveTypeId",EmployeeHasLeave.class)
                                                              .setParameter("empId",leaveAppliedDTO.getEmpId())
                                                              .setParameter("leaveTypeId",leaveAppliedDTO.getLeaveType())
                                                              .uniqueResult();
            employeeHasLeave.setNo_of_days(employeeHasLeave.getNo_of_days() - leaveApplied.getNo_of_days());
            sessionFactory.getCurrentSession().saveOrUpdate(employeeHasLeave);
        }
    }
}
