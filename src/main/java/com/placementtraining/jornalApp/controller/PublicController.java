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
    public String test() {
        System.out.println("get test hit");
        return "working";
    }

    @PostMapping
    public ResponseEntity<?> createuser(@RequestBody User user){
        try {
            System.out.println("Controller reached");
            userService.saveEntry(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Controller not  reached");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
