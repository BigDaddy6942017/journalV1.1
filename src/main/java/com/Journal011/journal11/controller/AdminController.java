package com.Journal011.journal11.controller;

import com.Journal011.journal11.entity.User;
import com.Journal011.journal11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        final List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody User user){
       try{
           userService.saveAdmin(user);
           return new ResponseEntity<>(HttpStatus.OK);
       } catch (Exception e){
           return new ResponseEntity<>(e,HttpStatus.FORBIDDEN);
       }
    }
}
