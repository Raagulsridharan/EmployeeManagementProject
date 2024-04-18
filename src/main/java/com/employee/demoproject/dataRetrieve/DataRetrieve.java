package com.employee.demoproject.dataRetrieve;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataRetrieve {

    @Autowired
    private SessionFactory sessionFactory;

    public Long getCount(String query){
        Long count = (Long) sessionFactory.getCurrentSession()
                .createQuery(query)
                .uniqueResult();
        return count;
    }

    public <T> List<T> processList(String query,Class<T> clazz) {
        Query<T> hibernateQuery = sessionFactory.getCurrentSession()
                .createQuery(query, clazz);
        return hibernateQuery.getResultList();
    }

    public <T> List<T> getListById(String query,Integer id,Class<T> clazz) {
        Query<T> hibernateQuery = sessionFactory.getCurrentSession()
                .createQuery(query, clazz)
                .setParameter("id",id);
        return hibernateQuery.getResultList();
    }

    public <T> T getObjectById(String query, Integer id, Class<T> clazz){
        Query<T> hibernateQuery = sessionFactory.getCurrentSession()
                .createQuery(query, clazz)
                .setParameter("id",id);
        return hibernateQuery.uniqueResult();
    }
}
