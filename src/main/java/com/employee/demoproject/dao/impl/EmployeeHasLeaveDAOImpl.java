package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.EmployeeHasLeaveDAO;
import com.employee.demoproject.dto.EmployeeHasLeaveDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeHasLeaveDAOImpl implements EmployeeHasLeaveDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void assignLeaveForEmployee(EmployeeHasLeaveDTO employeeHasLeaveDTO) {

    }
}
