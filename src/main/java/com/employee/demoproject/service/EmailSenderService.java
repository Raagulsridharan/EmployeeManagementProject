package com.employee.demoproject.service;

import com.employee.demoproject.entity.Department;
import com.employee.demoproject.exceptions.BusinessServiceException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DepartmentService departmentService;

    static Logger logger = Logger.getLogger(EmailSenderService.class);

    public void sendEmail(String toEmail, String password, Integer deptId) throws BusinessServiceException {
        Department department = departmentService.getDepartmentById(deptId);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            logger.info("Entering the email send service!!!");
            helper.setTo(toEmail);
            helper.setSubject("Your Login Credentials");
            helper.setText("Welcome To EMS\n\nYour username: " + toEmail + "\nYour password: " + password+"\nYour Department : "+department.getName()+
                    "\nFor Login : http://localhost:4200/login\nPlease login with your credential in above link and activate your account ASAP!" +
                    "\n\nThanks and Regards\nEmployee Management System.");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Error in sending the email service!!! "+e);
            throw new BusinessServiceException("Exception in sending email to employee",e);
        }
    }
}
