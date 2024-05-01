package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeHasLeaveDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import com.employee.demoproject.dto.LeaveAssignDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.EmployeeHasLeave;
import com.employee.demoproject.entity.LeavePolicy;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import com.employee.demoproject.service.LeavePolicyService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeHasLeaveDAOImpl implements EmployeeHasLeaveDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataRetrieve dataRetrieve;

    static Logger logger = Logger.getLogger(EmployeeHasLeaveDAOImpl.class);

    @Override
    public void assignLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTO) {
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class,empId);

        for(EmployeeHasLeaveDTO leavePolicy : employeeHasLeaveDTO){

            EmployeeHasLeave employeeHasLeave = new EmployeeHasLeave();
              employeeHasLeave.setEmployee_has_leave(employee);
            LeavePolicy leavePolicy1 = sessionFactory.getCurrentSession().get(LeavePolicy.class,leavePolicy.getLeaveId());
              employeeHasLeave.setLeavePolicy(leavePolicy1);
              employeeHasLeave.setNo_of_days(leavePolicy.getNoOfdays());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
              employeeHasLeave.setUpdated_on(sqlDate);
            sessionFactory.getCurrentSession().persist(employeeHasLeave);

            System.out.println("Successfully Leave assigned");
        }

    }

    @Override
    public void updateLeaveForEmployee(int empId, List<EmployeeHasLeaveDTO> employeeHasLeaveDTOList) {
        for(EmployeeHasLeaveDTO employeeHasLeaveDTO: employeeHasLeaveDTOList){
            EmployeeHasLeave employeeHasLeave = sessionFactory.getCurrentSession()
                    .createQuery("From EmployeeHasLeave ehl \n" +
                            "where ehl.employee_has_leave.id = :empId\n" +
                            "and ehl.leavePolicy.id = :leaveId",EmployeeHasLeave.class)
                    .setParameter("empId",empId)
                    .setParameter("leaveId",employeeHasLeaveDTO.getLeaveId())
                    .getSingleResult();
              employeeHasLeave.setNo_of_days(employeeHasLeaveDTO.getNoOfdays());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
              employeeHasLeave.setUpdated_on(sqlDate);
            sessionFactory.getCurrentSession().saveOrUpdate(employeeHasLeave);
        }
    }

    @Override
    public List<LeaveAssignDTO> getAllEmployeesLeaves() {
        List<LeaveAssignDTO> leavePolicies = sessionFactory.getCurrentSession()
                                                             .createQuery("select distinct e.id, e.name, d.name as dept, ds.role, ld.activated_on\n" +
                                                                     "from Employee e \n" +
                                                                     "join Department d \n" +
                                                                     "on e.department.id = d.id\n" +
                                                                     "join LoginDetails ld \n" +
                                                                     "on e.id = ld.employee_login.id \n" +
                                                                     "join EmpRoleSalary ers \n" +
                                                                     "on e.id = ers.employee_role_salary.id \n" +
                                                                     "join Designation ds \n" +
                                                                     "on ers.designation.id = ds.id\n" +
                                                                     "join EmployeeHasLeave ehl \n" +
                                                                     "on e.id = ehl.employee_has_leave.id",LeaveAssignDTO.class)
                                                             .getResultList();
        return leavePolicies;
    }

    @Override
    public List<EmployeeHasLeaveDTO> getEmployeeLeave(int empId) {
        List<EmployeeHasLeaveDTO> leaveDTOS = sessionFactory.getCurrentSession()
                                                            .createQuery("SELECT lp.id, lp.leave_types, ehl.no_of_days, ehl.updated_on\n" +
                                                                    "FROM EmployeeHasLeave ehl \n" +
                                                                    "join LeavePolicy lp \n" +
                                                                    "on ehl.leavePolicy.id = lp.id \n" +
                                                                    "where ehl.employee_has_leave.id = :empId",EmployeeHasLeaveDTO.class)
                                                            .setParameter("empId",empId)
                                                            .getResultList();
        return leaveDTOS;
    }

    @Override
    public Long totalEmployeeHasLeave() throws DataServiceException {
        try{
            logger.info("getting count of Employee has Leave");
            String query = "SELECT COUNT(*)\n" +
                    "FROM (\n" +
                    "    SELECT COUNT(*) AS employee_count\n" +
                    "    FROM EmployeeHasLeave\n" +
                    "    GROUP BY employee_has_leave\n" +
                    ") AS subquery_result\n";
            Long count = dataRetrieve.getCount(query);
            return count;
        }catch (DataAccessException e){
            logger.error("Error in getting count of Employee has leave in business layer. "+e);
            throw new DataServiceException("Exception in data accessing in business layer for getting count of Employee has leave",e);
        }
    }

    @Override
    public List<LeaveAssignDTO> filterEmployeeHasLeave(FilterOption filterOption) throws DataServiceException {
        try {
            logger.info("Entering the method for filtering the Employee has leave");
            Integer firstResult = (filterOption.getPageNo() * filterOption.getPageSize()) - filterOption.getPageSize();
            StringBuilder queryParam = new StringBuilder("select distinct e.id, e.name, d.name as dept, ds.role, ld.activated_on\n" +
                    "from Employee e \n" +
                    "join Department d \n" +
                    "on e.department.id = d.id\n" +
                    "join LoginDetails ld \n" +
                    "on e.id = ld.employee_login.id \n" +
                    "join EmpRoleSalary ers \n" +
                    "on e.id = ers.employee_role_salary.id \n" +
                    "join Designation ds \n" +
                    "on ers.designation.id = ds.id\n" +
                    "join EmployeeHasLeave ehl \n" +
                    "on e.id = ehl.employee_has_leave.id");
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                queryParam.append(" WHERE ers.employee_role_salary.name LIKE :searchKey1 OR ers.employee_role_salary.department.name LIKE :searchKey2 OR ers.designation.role LIKE :searchKey3");
            }
            Query query = sessionFactory.getCurrentSession().createQuery(queryParam.toString());
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                query.setParameter("searchKey1", "%" + filterOption.getSearchKey() + "%")
                        .setParameter("searchKey2", "%" + filterOption.getSearchKey() + "%")
                        .setParameter("searchKey3", "%" + filterOption.getSearchKey() + "%");
            }
            query.setFirstResult(firstResult);
            query.setMaxResults(filterOption.getPageSize());
            List<LeaveAssignDTO> leaveAssignDTOS = query.list();
            return leaveAssignDTOS;
        }catch (DataAccessException e){
            logger.error("Error in business layer for filtering Employee has leave. "+e);
            throw new DataServiceException("Exception in business layer for filtering Employee has leave",e);
        }
    }


}
