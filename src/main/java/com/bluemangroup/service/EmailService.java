package com.bluemangroup.service;

import com.bluemangroup.model.EmailRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private JavaMailSenderImpl javaMailSender;

    public EmailService() {

        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("mail.unigroupinc.com");
        javaMailSender.setPort(25);

        javaMailSender.setUsername("my.gmail@gmail.com");
        javaMailSender.setPassword("password");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");

    }

    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("donotreply@unigroupinc.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);

    }

}
