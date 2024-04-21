package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.LeaveAppliedDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
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

    @Autowired
    private DataRetrieve dataRetrieve;

    @Override
    public Long getApprovedLeaveCount() {
        String query = "SELECT count(la) \n" +
                "FROM LeaveApplied la \n" +
                "where la.status = 'Approved'";
        return dataRetrieve.getCount(query);
    }

    @Override
    public Long getRejectedLeaveCount() {
        String query = "SELECT count(la) \n" +
                "FROM LeaveApplied la \n" +
                "where la.status = 'Rejected'";
        return dataRetrieve.getCount(query);
    }

    @Override
    public Long getRequestedLeaveCount() {
        String query = "SELECT count(la) \n" +
                "FROM LeaveApplied la \n" +
                "where la.status = 'Requested'";
        return dataRetrieve.getCount(query);
    }

    @Override
    public List<LeaveApplied> getAllApprovedLeaves() {
        String query = "FROM LeaveApplied la where la.status = 'Approved'";
        return dataRetrieve.processList(query,LeaveApplied.class);
    }

    @Override
    public List getAllRejectedLeaves() {
        String query = "FROM LeaveApplied la where la.status = 'Rejected'";
        return dataRetrieve.processList(query,LeaveApplied.class);
    }

    @Override
    public List<LeaveApplied> getAllRequestedLeaves() {
        String query = "FROM LeaveApplied la where la.status = 'Requested'";
        return dataRetrieve.processList(query,LeaveApplied.class);
    }

    @Override
    public List<LeaveApplied> getLeaveHistoryBYEmployee(int empId) {
        String query = "FROM LeaveApplied la where la.employee_leave_applied.id = :id";
        List<LeaveApplied> employeeLeaveHistory = dataRetrieve.getListById(query,empId,LeaveApplied.class);
        return employeeLeaveHistory;
    }

    @Override
    public String empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO) {
        LeaveApplied leaveApplied  = sessionFactory.getCurrentSession()
                .createQuery("FROM LeaveApplied l WHERE l.status =:status AND l.employee_leave_applied.id = :empId AND :fromDate BETWEEN l.from_date AND l.to_date",LeaveApplied.class)
                .setParameter("status","requested")
                .setParameter("empId",empId)
                .setParameter("fromDate",leaveAppliedDTO.getFromDate())
                .uniqueResult();

        if(leaveApplied==null){
            Employee employee = sessionFactory.getCurrentSession().get(Employee.class,empId);
            LeavePolicy leavePolicy = sessionFactory.getCurrentSession().get(LeavePolicy.class,leaveAppliedDTO.getLeaveType());
            long daysBetween = ChronoUnit.DAYS.between(leaveAppliedDTO.getFromDate().toLocalDate(), leaveAppliedDTO.getToDate().toLocalDate());
            int daysBetweenAsInt = Math.toIntExact(daysBetween); // Convert long to int
            Integer noOfDays = Integer.valueOf(daysBetweenAsInt); // Create Integer object from int value

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
                .createQuery("FROM LeaveApplied l " +
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
                                                              .createQuery("from EmployeeHasLeave e \n" +
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
