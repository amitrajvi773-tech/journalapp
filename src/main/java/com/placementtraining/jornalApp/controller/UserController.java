package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.config.SpringSecurity;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    private SpringSecurity springSecurity;

    @GetMapping
    public User getUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userService.findByUsername(username);
    }


    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userindb=userService.findByUsername(username);
             userindb.setUsername(user.getUsername());
             userindb.setPassword(user.getPassword());
             userService.saveEntry(userindb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}