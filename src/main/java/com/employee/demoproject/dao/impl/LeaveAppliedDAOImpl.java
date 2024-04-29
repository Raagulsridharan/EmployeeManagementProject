package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.LeaveAppliedDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.LeaveAppliedDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeaveApplied;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import org.apache.log4j.Logger;
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

    static Logger logger = Logger.getLogger(LeaveAppliedDAOImpl.class);

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
    public LeaveApplied empApplyingLeave(int empId, LeaveAppliedDTO leaveAppliedDTO) {
        LeaveApplied leaveApplied  = sessionFactory.getCurrentSession()
                .createQuery("FROM LeaveApplied l WHERE l.status =:status AND l.employee_leave_applied.id = :empId AND :fromDate BETWEEN l.from_date AND l.to_date",LeaveApplied.class)
                .setParameter("status","requested")
                .setParameter("empId",empId)
                .setParameter("fromDate",leaveAppliedDTO.getFromDate())
                .uniqueResult();
        if(leaveApplied==null){
            leaveApplied = new LeaveApplied();
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
            return leaveApplied;
        }else{
            return null;
        }
    }

    @Override
    public void updateLeaveStatus(LeaveAppliedDTO leaveAppliedDTO) {
        LeaveApplied leaveApplied  = sessionFactory.getCurrentSession()
                .get(LeaveApplied.class,leaveAppliedDTO.getId());

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

    @Override
    public Long getEmployeeLeaveHistoryCount(int empId) throws DataServiceException{
        try{
            logger.info("Getting employee leave history count");
            String query = "SELECT count(*) FROM LeaveApplied la where la.employee_leave_applied.id = :id";
            Long count = dataRetrieve.getCountById(query,empId);
            return count;
        }catch (DataAccessException e){
            logger.error("Error in getting employee leave history count in business layer. "+e);
            throw new DataServiceException("Exception in getting employee leave history count in business layer",e);
        }
    }
}
