package com.employee.demoproject.service;

import com.employee.demoproject.entity.Department;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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

    public void sendEmail(String toEmail, String password, Integer deptId) {
        Department department = departmentService.getDepartmentById(deptId);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(toEmail);
            helper.setSubject("Your Login Credentials");
            helper.setText("Your username: " + toEmail + "\nYour password: " + password+"\nYour Department : "+department.getName());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
