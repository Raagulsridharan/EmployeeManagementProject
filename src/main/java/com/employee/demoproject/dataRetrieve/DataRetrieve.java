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

    /**
     * getting count of given query
     * @param query
     * @return
     * @throws DataAccessException
     */
    public Long getCount(String query) throws DataAccessException{
        try {
            logger.info("Getting count");
            Long count = (Long) sessionFactory.getCurrentSession()
                    .createQuery(query)
                    .uniqueResult();
            return count;
        }catch (Exception e){
            logger.error("Error in getting count");
            throw new DataAccessException("Exception data access for getting count");
        }

    }

    /**
     * getting count for given query with the id
     * @param query
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Long getCountById(String query, Integer id) throws DataAccessException{
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

    /**
     * getting list wit respectable class
     * @param query
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
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

    /**
     * getting list with respectable class using id
     * @param query
     * @param id
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> getListById(String query,Integer id,Class<T> clazz) throws DataAccessException{
        try {
            logger.info("Entering in data access layer");
            Query<T> hibernateQuery = sessionFactory.getCurrentSession()
                    .createQuery(query, clazz)
                    .setParameter("id",id);
            return hibernateQuery.getResultList();
        }catch (Exception e){
            logger.error("Error in data access layer");
            throw new DataAccessException("Exception in data access",e);
        }
    }

    /**
     * getting object of respectable class using id
     * @param query
     * @param id
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> T getObjectById(String query, Integer id, Class<T> clazz) throws DataAccessException{
        try {
            logger.info("Entering in data access");
            Query<T> hibernateQuery = sessionFactory.getCurrentSession()
                    .createQuery(query, clazz)
                    .setParameter("id",id);
            return hibernateQuery.uniqueResult();
        }catch (Exception e){
            logger.error("Error in data access");
            throw new DataAccessException("Exception in data access",e);
        }
    }
}
