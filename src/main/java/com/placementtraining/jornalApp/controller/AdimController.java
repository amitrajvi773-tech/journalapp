package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdimController {
    @Autowired
    UserService userService;

    @GetMapping("/alluser")
    public ResponseEntity<?> Role(){
 List<User> user =userService.getAll();
 if(user!=null){
return new ResponseEntity<>(user, HttpStatus.OK);
 }
 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
