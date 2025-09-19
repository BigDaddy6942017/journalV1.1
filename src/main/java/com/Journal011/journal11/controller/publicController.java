package com.Journal011.journal11.controller;


import com.Journal011.journal11.entity.User;
import com.Journal011.journal11.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired; // a , b =>
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.rmi.server.LogStream.log;

@Slf4j
@RestController
@RequestMapping ("/public")
public class publicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
       try {
           userService.saveNewEntry(user);
           log.info("user created",HttpStatus.CREATED);
       }catch (Exception e){
           log.error("user not created due to: ", e);
       }
    }

}
