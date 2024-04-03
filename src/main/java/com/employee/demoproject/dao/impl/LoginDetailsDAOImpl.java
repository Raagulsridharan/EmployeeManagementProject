package com.employee.demoproject.dao.impl;

import com.employee.demoproject.dao.LoginDetailsDAO;
import com.employee.demoproject.dto.EmployeeDTO;
import com.employee.demoproject.entity.Employee;
import com.employee.demoproject.entity.LoginDetails;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;

@Repository
public class LoginDetailsDAOImpl implements LoginDetailsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createLogin(Employee employee) {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUsername(employee.getEmail());
        loginDetails.setPassword(generatePassword());
        loginDetails.setFlag(0);
        loginDetails.setEmployee_login(employee);

        sessionFactory.getCurrentSession().persist(loginDetails);
    }

    @Override
    public void updateUserName(int id, EmployeeDTO employeeDTO) {
        LoginDetails login = (LoginDetails) sessionFactory.getCurrentSession()
                .createQuery("select l from LoginDetails l where l.employee_login.id = :id")
                .setParameter("id",id)
                .uniqueResult();
        login.setUsername(employeeDTO.getEmail());

        sessionFactory.getCurrentSession().saveOrUpdate(login);
    }

    @Override
    public void updatePassword(int id, String password) {
        LoginDetails login = (LoginDetails) sessionFactory.getCurrentSession()
                .createQuery("select l from LoginDetails l where l.employee_login.id = :id")
                .setParameter("id",id)
                .uniqueResult();
        login.setPassword(password);

        sessionFactory.getCurrentSession().saveOrUpdate(login);
    }


    public static String generatePassword() {
        final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        final String NUMBER = "123456789";
        final String OTHER_CHAR = "!@#$%&*+";

        String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        String allowChar = PASSWORD_ALLOW_BASE;

        password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        password.append(OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length())));

        for (int i = 4; i < 8; i++) {
            password.append(allowChar.charAt(random.nextInt(allowChar.length())));
        }
        return password.toString();
    }
}