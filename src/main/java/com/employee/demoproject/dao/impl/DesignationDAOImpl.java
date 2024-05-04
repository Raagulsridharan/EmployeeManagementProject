package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.DesignationDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.entity.Designation;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignationDAOImpl implements DesignationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataRetrieve dataRetrieve;

    static Logger logger = Logger.getLogger(DesignationDAOImpl.class);

    /**
     * Creates a new designation.
     *
     * @param designation The designation object to be created.
     * @return The newly created designation object.
     * @throws DataServiceException If an error occurs while creating the designation in the data service layer.
     */
    @Override
    public Designation createDesignation(Designation designation) throws DataServiceException{
        try {
            logger.info("Entering to save the designation");
            sessionFactory.getCurrentSession().persist(designation);
            System.out.println("Designation created...!!!");
            return designation;
        }catch (Exception e){
            logger.error("Error in in Dao layer to save designation. "+e);
            throw new DataServiceException("Exception in Dao layer to save designation",e);
        }

    }

    /**
     * Updates an existing designation with the given ID.
     *
     * @param id The ID of the designation to update.
     * @param designation The updated designation object containing new data.
     * @return The updated designation object.
     * @throws DataServiceException If an error occurs while updating the designation in the data service layer.
     */
    @Override
    public Designation updateDesignation(int id, Designation designation) throws DataServiceException{
        try {
            logger.info("Entering to update designation in DAO layer");
            Designation updateDesignation = sessionFactory.getCurrentSession().get(Designation.class,id);
            updateDesignation.setRole(designation.getRole());
            updateDesignation.setSalary_package(designation.getSalary_package());
            sessionFactory.getCurrentSession().saveOrUpdate(updateDesignation);
            System.out.println("Designation updated...!!!");
            return updateDesignation;
        }catch (Exception e){
            logger.error("Error in in updating designation...in DAO layer. "+e);
            throw new DataServiceException("Exception in updating designation...in DAO layer",e);
        }

    }

    @Override
    public List<Designation> getAllDesignation() {
        List<Designation> designationList = sessionFactory.getCurrentSession()
                                                        .createQuery("from Designation d")
                                                        .getResultList();
        return designationList;
    }

    @Override
    public Designation getDesignationById(int id) {
        Designation designation = sessionFactory.getCurrentSession().get(Designation.class,id);
        return designation;
    }

    @Override
    public Designation getDesignationByRole(String role) {
        Query<Designation> query = sessionFactory.getCurrentSession()
                .createQuery("from Designation d where d.role = :role", Designation.class)
                .setParameter("role", role);
        return query.uniqueResult();
    }

    @Override
    public Long getDesignationCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select count(d) from Designation d", Long.class);
        return query.uniqueResult();
    }

    @Override
    public String getDesignationByEmail(String email) {
        Object obj = sessionFactory.getCurrentSession()
                                    .createQuery("select ds.role\n" +
                                            "from Employee e \n" +
                                            "join EmpRoleSalary ers \n" +
                                            "on e.id = ers.employee_role_salary.id \n" +
                                            "join Designation ds \n" +
                                            "on ers.designation.id = ds.id \n" +
                                            "where CAST(e.email AS binary) = CAST(:email AS binary)")
                                    .setParameter("email",email)
                                    .uniqueResult();
        String role  = String.valueOf(obj);
        return role;
    }

    @Override
    public List<Designation> getRolesByDepartment(Integer deptId) throws DataServiceException {
        try {
            logger.info("Fetching roles by department!");
            String query = "From Designation ds Where ds.department.id=:id";
            List<Designation> designations = dataRetrieve.getListById(query,deptId,Designation.class);
            return designations;
        }catch (DataAccessException e){
            logger.error("Error in accessing designations in DAO, "+e);
            throw new DataServiceException("Exception in fetching designations by department.",e);
        }
    }
}
