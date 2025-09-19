package com.Journal011.journal11.service;

import com.Journal011.journal11.entity.User;
import com.Journal011.journal11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //mongo db repo..instance....

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //xy
        User user = userRepository.findByUserName(username); //[]yug...[20]
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder().
                    username(user.getUserName()).
                    password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])) //[0]
                    .build();
        }
        throw new UsernameNotFoundException("user not found with username " + username);
    }
}
