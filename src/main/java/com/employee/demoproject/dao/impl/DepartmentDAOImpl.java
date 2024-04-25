package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.DepartmentDAO;
import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.DataAccessException;
import com.employee.demoproject.exceptions.DataServiceException;
import com.employee.demoproject.pagination.FilterOption;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String createDepartment(Department department) {
        try {
            sessionFactory.getCurrentSession().persist(department);
            return "Department successfully persisted...!!!";
        }catch (Exception e){
            return "Failed to create department: " + e.getMessage();
        }
    }

    @Override
    public void updateDepartment(Department department) {
        sessionFactory.getCurrentSession().saveOrUpdate(department);
        System.out.println("updated... Department");
    }

    @Override
    public List<Department> getAllDepartment() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Department");
        List<Department> departmentList = query.list();
        return departmentList;
    }

    @Override
    public Department getDepartmentById(int id) {
        return sessionFactory.getCurrentSession().get(Department.class,id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        Query<Department> query = sessionFactory.getCurrentSession()
                .createQuery("from Department d where d.name = :deptName")
                .setParameter("deptName", name);
        return query.uniqueResult();
    }



    @Override
    public Long getDepartmentCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select count(d) from Department d", Long.class);
        return query.uniqueResult();
    }

    @Override
    public List<Department> filterDepartment(FilterOption filterOption) {
        try {
            Integer firstResult = (filterOption.getPageNo() * filterOption.getPageSize()) - filterOption.getPageSize();

            StringBuilder queryParam = new StringBuilder("FROM Department d");
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                queryParam.append(" WHERE d.name LIKE :searchKey");
            }

            Query query = sessionFactory.getCurrentSession().createQuery(queryParam.toString());
            if (filterOption.getSearchKey() != null && !filterOption.getSearchKey().isEmpty()) {
                query.setParameter("searchKey", "%"+filterOption.getSearchKey()+ "%");
            }
            query.setFirstResult(firstResult);
            query.setMaxResults(filterOption.getPageSize());

            List<Department> departmentList = query.list();

            return departmentList;
        }catch (DataAccessException e){
            throw new DataServiceException("Exception in accessing the department for filtering", e);
        }
    }


}
