package com.Journal011.journal11.controller;


import com.Journal011.journal11.entity.User;
import com.Journal011.journal11.repository.UserRepository;
import com.Journal011.journal11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService; ///service ...controller -> req..@Body data...-> server(j : data)..database..
    /// //usercontoller -> userService:

  @Autowired
  private UserRepository userRepository;


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAll();
            users.forEach(user -> System.out.println("User: " + user.getUserName()));
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace(); // Full stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody User user) {
        try {
              userService.saveNewEntry(user);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

        @PutMapping
        public ResponseEntity<?> updateUser(@RequestBody User user) {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                final User userInDb = userService.findByUserName(userName);
                userInDb.setUserName(user.getUserName());
                userInDb.setPassword(user.getPassword());
                userService.saveNewEntry(userInDb);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                System.out.println("lwdee ");
               return new ResponseEntity<> (HttpStatus.NO_CONTENT);
        }}
    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userRepository.findByUserName(userName);

            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            userRepository.delete(user);
            System.out.println("Deleted user: " + userName);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return new ResponseEntity<>("Deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
