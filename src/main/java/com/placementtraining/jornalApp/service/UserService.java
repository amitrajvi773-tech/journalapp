package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);we dont use because
//    we are using SL4J

    public User saveNewUser(User user){
        try{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserrole(new ArrayList<>(Arrays.asList("USER")));
        userRepository.save(user);}
        catch (Exception e) {
          log.error("DUPLICATE NAME OCCUR",e);
          throw e;
           }
        return user;
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }


    public Optional<User> getid(Integer myid){
        return userRepository.findById(myid);
    }

    public List<User> getAll(){
      return   userRepository.findAll();
    }

    public void deleteid(Integer id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


}