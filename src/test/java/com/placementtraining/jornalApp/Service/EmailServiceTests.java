package com.placementtraining.jornalApp.Service;

import com.placementtraining.jornalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail(){
        emailService.sendMail("amit773@gmail.com",
                "This is my first email",
                "Hi, aap kaise ho?");
    }

}
