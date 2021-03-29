package com.bigbank.stocktracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.repository.UserRepository;
import com.bigbank.stocktracker.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
   
    @Autowired
    UserService userService;
    
    
    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User newUser) {
    	User registeredUser = userService.register(newUser);
    	return ResponseEntity.ok().body(registeredUser.toString());
    }
    
    @GetMapping("/users")
    public  List<User> getAllUsers() {
    	return userService.getAllUsers();
    }
    
}