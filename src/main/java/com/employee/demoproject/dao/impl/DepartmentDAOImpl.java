package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.dataRetrieve.DataRetrieve;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.exceptions.HttpClientException;
import com.employee.demoproject.pagination.FilterOption;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataRetrieve dataRetrieve;

    static Logger logger = Logger.getLogger(DepartmentDAOImpl.class);

    @Override
    public Department createDepartment(String name) throws DataServiceException, HttpClientException {
        try {
            logger.info("Create Department initialized...");
            Department department = new Department();
            department.setName(name);
            sessionFactory.getCurrentSession().persist(department);
            return department;
        } catch (Exception e) {
            logger.error("Error in creating department..." + e);
            if(e instanceof ConstraintViolationException && e.getMessage().contains("Duplicate entry")){
                throw new HttpClientException("Department name " + name + " already exists", HttpStatus.CONFLICT.value());
            } else{
                throw new DataServiceException(e.getMessage(), e);
            }

        }
    }

    @Override
    public Department updateDepartment(Integer id, String name) throws DataServiceException {
        try {
            logger.info("Update department initialized");
            String query = "From Department d where d.id = :id";
            Department updatedDepartment = dataRetrieve.getObjectById(query, id, Department.class);
            updatedDepartment.setName(name);
            sessionFactory.getCurrentSession().saveOrUpdate(updatedDepartment);
            System.out.println("updated... Department");
            return updatedDepartment;
        } catch (Exception e) {
            logger.error("Error in updating department " + e);
            throw new DataServiceException("Exception in updating", e);
        }
    }

    @Override
    public List<Department> getAllDepartment() throws DataServiceException {
        try {
            logger.info("All departments will retrieving!");
            String query = "FROM Department";
            List<Department> departmentList = dataRetrieve.processList(query, Department.class);
            return departmentList;
        } catch (DataAccessException e) {
            logger.error("Error in retrieving all departments! " + e);
            throw new DataServiceException("Exception in getting all department", e);
        }
    }

    @Override
    public Department getDepartmentById(int id) throws DataServiceException{
        try {
            logger.info("Initialize the getting department by it's ID.");
            return sessionFactory.getCurrentSession().get(Department.class, id);
        }catch (Exception e){
            logger.error("Error in getting department by it's ID. "+e);
            throw new DataServiceException("Exception in getting department by it's id. "+e);
        }

    }

    @Override
    public Department getDepartmentByName(String name) throws DataServiceException{
        try{
            logger.info("Getting department by it's name");
            Query<Department> query = sessionFactory.getCurrentSession()
                    .createQuery("from Department d where d.name = :deptName")
                    .setParameter("deptName", name);
            return query.uniqueResult();
        }catch (Exception e){
            logger.error("Error in getting department by it's name. "+e);
            throw new DataServiceException("Exception in getting department by name. ", e);
        }

    }

    @Override
    public Long getDepartmentCount() {
        try {
            logger.info("Entering Department count method...");
            Query<Long> query = sessionFactory.getCurrentSession()
                    .createQuery("select count(d) from Department d", Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error("Error in getting department count. " + e);
            throw new DataServiceException("Exception in getting department count. ", e);
        }

    }

    @Override
    public List<Department> filterDepartment(FilterOption filterOption) throws DataServiceException {
        try {
            logger.info("Entering the method of fetch department by filtering");
            Integer firstResult = (filterOption.getPageNo() * filterOption.getPageSize()) - filterOption.getPageSize();

            StringBuilder queryParam = new StringBuilder("FROM Department d");
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                queryParam.append(" WHERE d.name LIKE :searchKey");
            }

            Query query = sessionFactory.getCurrentSession().createQuery(queryParam.toString());
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                query.setParameter("searchKey", "%" + filterOption.getSearchKey() + "%");
            }
            query.setFirstResult(firstResult);
            query.setMaxResults(filterOption.getPageSize());

            List<Department> departmentList = query.list();

            return departmentList;
        } catch (Exception e) {
            logger.error("Error found in filter departments"+e);
            throw new DataServiceException("Exception in accessing the department for filtering", e);
        }
    }


}
