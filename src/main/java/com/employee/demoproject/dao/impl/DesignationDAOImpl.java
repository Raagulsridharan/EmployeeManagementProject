package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.DesignationDAO;
import com.employee.demoproject.entity.Designation;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignationDAOImpl implements DesignationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createDesignation(Designation designation) {
        sessionFactory.getCurrentSession().persist(designation);
        System.out.println("Designation created...!!!");
    }

    @Override
    public void updateDesignation(int id, Designation designation) {
        Designation updateDesignation = sessionFactory.getCurrentSession().get(Designation.class,id);
        updateDesignation.setRole(designation.getRole());
        updateDesignation.setSalary_package(designation.getSalary_package());
        sessionFactory.getCurrentSession().saveOrUpdate(updateDesignation);
        System.out.println("Designation updated...!!!");
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
}
