package com.example.MiniSplitwise.controller;

import com.example.MiniSplitwise.service.UserService;
import com.example.MiniSplitwise.dto.UserDTO;
import com.example.MiniSplitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.io.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        logger.info("Return all users");
        try {
            List<User> users = userService.getAllUsers();

            if (users != null) {
                return ResponseEntity.ok(users);
            } else {
                // Handle the case where the list is null
                logger.info("User list is null");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id){
        logger.info("Running find user by ID");
        Optional<User> userOptional = userService.getUserById(id);

        // Check if user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            // Return a 404 Not Found response if user is not present
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") UUID id){
        logger.info("Running delete user by id");
        userService.deleteUser(id);
    }
}
