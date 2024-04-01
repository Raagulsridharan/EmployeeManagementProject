package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<EmployeeDTO> getAllEmployee(){
        Query<EmployeeDTO> query = sessionFactory.getCurrentSession()
                .createNativeQuery("select e.id,e.name as emp_name, e.gender, e.mobile, e.email, e.address, d.name as department, e.activated_on \n" +
                "from employee e\n" +
                "left join department d\n" +
                "on e.dept_id = d.id;",EmployeeDTO.class);
        List<EmployeeDTO> empList = query.getResultList();
        return empList;
    }

    @Override
    public EmployeeDTO getEmployeeById(int id){
        Query<EmployeeDTO> query = sessionFactory.getCurrentSession()
                .createQuery("select e.id,e.name as emp_name, e.gender, e.mobile, e.email, e.address, d.name as department, e.activated_on \n" +
                        "from Employee e\n" +
                        "left join Department d\n" +
                        "on e.department.id = d.id\n" +
                        "where e.id =:emp_id",EmployeeDTO.class)
                .setParameter("emp_id",id);
        return query.uniqueResult();
    }

    @Override
    public Long getTotalEmployeeCount() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Employee",Long.class);
        return query.uniqueResult();
    }
}
