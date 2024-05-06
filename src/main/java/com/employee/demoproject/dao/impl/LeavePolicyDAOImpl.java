package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.LeavePolicyDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeavePolicyDAOImpl implements LeavePolicyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataRetrieve dataRetrieve;

    static Logger logger = Logger.getLogger(LeavePolicyDAOImpl.class);

    @Override
    public void creatLeavePolicy(LeavePolicy leavePolicy) {
        sessionFactory.getCurrentSession().persist(leavePolicy);
        System.out.println("Leave policy created...");
    }

    @Override
    public List<LeavePolicy> getAllLeavePolicy() {
        List<LeavePolicy> designationList = sessionFactory.getCurrentSession()
                .createQuery("select lp from LeavePolicy lp")
                .getResultList();
        return designationList;
    }

    @Override
    public LeavePolicy getLeavePolicyById(int id) {
        return sessionFactory.getCurrentSession().get(LeavePolicy.class,id);
    }

    @Override
    public LeavePolicy getLeavePolicyByName(String name) {
        Query<LeavePolicy> query = sessionFactory.getCurrentSession()
                .createQuery("select lp from LeavePolicy lp where lp.leave_types = :leaveName", LeavePolicy.class)
                .setParameter("leaveName", name);
        return query.uniqueResult();
    }

    @Override
    public Long getLeaveTypesCount() {
        Long count = (Long) sessionFactory.getCurrentSession()
                                        .createQuery("SELECT count(lp) FROM LeavePolicy lp")
                                        .uniqueResult();
        return count;
    }

    @Override
    public List<LeavePolicy> getEmployeeLeave(Integer empId) throws DataServiceException {
        try {
            logger.info("Fetching employee leave policy");
            String query = "Select e.leavePolicy From EmployeeHasLeave e Where e.employee_has_leave.id = :id And e.no_of_days > 0";
            List<LeavePolicy> leavePolicies = dataRetrieve.getListById(query,empId,LeavePolicy.class);
            return leavePolicies;
        }catch (DataAccessException e){
            logger.error("Error in fetching employee leaves. "+e);
            e.printStackTrace();
            throw new DataServiceException("Exception in fetching employees leaves",e);
        }
    }
}
