package com.placementtraining.jornalApp.Schedular;

import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.UserRepositoryImp;
import com.placementtraining.jornalApp.service.EmailService;
import com.placementtraining.jornalApp.service.UserDetailServiceimp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserSchedular {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    public void fetchUserAndSaMail(){
        List<User> all= userRepositoryImp.getUserBySA();
        
    }
}
