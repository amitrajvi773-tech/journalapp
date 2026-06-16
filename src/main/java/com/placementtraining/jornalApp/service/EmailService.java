package com.placementtraining.jornalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to,String subject,String body){
         try{
             SimpleMailMessage mail=new SimpleMailMessage();
             mail.setTo(to);
             mail.setSubject(subject);
             mail.setText(body);

             javaMailSender.send(mail);

             System.out.println("Email sent successfully");


         } catch (Exception e) {
             log.error("exception while sending email",e);
         }
    }
}


