package com.Journal011.journal11.service;

import com.Journal011.journal11.entity.User;
import com.Journal011.journal11.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewEntry(User user) { //...username , password
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e) {
           log.info("lol hahahhaha",e);
        }

    }

    public void saveAdmin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Exception while saving admin user", e);
        }
    }


    public void saveUser(User user) {
        if (!user.getPassword().startsWith("$2a$")) { // typical BCrypt prefix
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

public List<User> getAll(){
    return userRepository.findAll();
}

public Optional<User> findById(ObjectId id){
    return userRepository.findById(id);
}

    public void deleteById(ObjectId id){
    userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
    return userRepository.findByUserName(userName);
    }
}
