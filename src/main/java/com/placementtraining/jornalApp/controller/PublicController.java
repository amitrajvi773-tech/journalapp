package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public void testing(){
        System.out.println("hello testing happening");
    }

    @PostMapping
    public ResponseEntity<?> createuser(@RequestBody User user){
        try {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);        }
    }
}
