package com.bigbank.stocktracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigbank.stocktracker.model.Stock;
import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	Logger logger = LogManager.getLogger(UserController.class);
	
    @Autowired
    UserService userService;
    
    
    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {
    	logger.info("registerUser()");
    	User registeredUser = userService.register(newUser);
    	return ResponseEntity.ok().body(registeredUser);
    }
    
    @PutMapping("/users/favourites/{symbol}")
    public ResponseEntity<List<Stock>> addStockToFavourites(@PathVariable(value = "symbol") String symbol) {
    	return ResponseEntity.ok().body(userService.addStockToFavourites(symbol));
    }
    
    @DeleteMapping("/users/favourites/{symbol}")
    public ResponseEntity<List<Stock>> removeStockFromFavourites(@PathVariable(value = "symbol") String symbol) {
    	return ResponseEntity.ok().body(userService.removeStockFromFavourites(symbol));
    }
    
    
    @GetMapping("/users")
    public  List<User> getAllUsers() {
    	return userService.getAllUsers();
    }
    
}