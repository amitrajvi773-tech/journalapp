package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceimp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user= userRepository.findByUsername(username);
      if(user !=null ){
          return org.springframework.security.core.userdetails.User.builder()
                  .username(user.getUsername())
                  .password(user.getPassword())
                  .roles(user.getUserrole().toArray(new String[0])).
                  build();
      }
        throw new UsernameNotFoundException(" user not found " + username);
    }
}
