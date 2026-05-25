package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getalluser(){
      return  userService.getALL();
    }

    @PostMapping
    public void createuser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateuser(@RequestBody User user,@PathVariable String username){
        User userindb=userService.findByUsername(username);
        if(userindb !=null){
             userindb.setUsername(user.getUsername());
             userindb.setPassword(user.getPassword());
             userService.saveEntry(userindb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}