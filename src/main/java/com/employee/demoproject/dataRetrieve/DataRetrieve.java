package com.employee.demoproject.dataRetrieve;

import com.employee.demoproject.exceptions.DataAccessException;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataRetrieve {

    @Autowired
    private SessionFactory sessionFactory;

    static Logger logger = Logger.getLogger(DataRetrieve.class);

    public Long getCount(String query){
        Long count = (Long) sessionFactory.getCurrentSession()
                .createQuery(query)
                .uniqueResult();
        return count;
    }
    public Long getCountById(String query, Integer id){
        try{
            logger.info("Getting count by id");
            Long count = (Long) sessionFactory.getCurrentSession()
                    .createQuery(query)
                    .setParameter("id",id)
                    .uniqueResult();
            return count;
        }catch (Exception e){
            logger.error("Error in accessing data for getting count by id. "+e);
            throw new DataAccessException("Exception data access for getting count",e);
        }

    }

    public <T> List<T> processList(String query,Class<T> clazz) throws DataAccessException{
        try{
            logger.info("Initialise retrieving data for '"+clazz+"' this class");
            Query<T> hibernateQuery = sessionFactory.getCurrentSession()
                    .createQuery(query, clazz);
            return hibernateQuery.getResultList();
        }catch (Exception e){
            logger.error("Error in accessing data"+e);
            throw new DataAccessException("Exception in accessing data. ",e);
        }
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
