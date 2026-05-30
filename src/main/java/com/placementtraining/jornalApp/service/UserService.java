package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.config.SpringSecurity;
import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserrole(new ArrayList<>(Arrays.asList("USER")));
        userRepository.save(user);
    }

//    public List<User> getALL() {
//        return userRepository.findAll();
//    }

    public Optional<User> getid(Integer myid){

        return userRepository.findById(myid);
    }

    public void deleteid(Integer id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


}