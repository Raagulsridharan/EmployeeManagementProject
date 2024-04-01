package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createDepartment(Department department) {
        sessionFactory.getCurrentSession().persist(department);
        System.out.println("Department successfully persisted...!!!");
    }

    @Override
    public void updateDepartment(Department department) {
        sessionFactory.getCurrentSession().saveOrUpdate(department);
        System.out.println("updated... Department");
    }

    @Override
    public List<Department> getAllDepartment() {
        NativeQuery nativeQuery = sessionFactory.getCurrentSession().createNativeQuery("select * from department");
        List<Department> departmentList = nativeQuery.getResultList();
        return departmentList;
    }

    @Override
    public Department getDepartmentById(int id) {
        return sessionFactory.getCurrentSession().get(Department.class,id);
    }

    @Override
    public Long getDepartmentCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Department", Long.class);
        return query.uniqueResult();
    }
}
