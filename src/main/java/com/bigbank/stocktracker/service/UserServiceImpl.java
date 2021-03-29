package com.bigbank.stocktracker.service;

import static com.bigbank.stocktracker.util.ErrorReason.USER_ALREADY_EXISTS;
import static com.bigbank.stocktracker.util.ErrorReason.USER_NOT_EXISTS;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
    public User register(User user) {
    	if(userRepository.findByUserName(user.getUsername()).isPresent()) {
    		throw new UserAlreadyExistsException(USER_ALREADY_EXISTS.toString());
    	} else {
    		user.setPassword(passwordEncoder.encode(user.getPassword()));
    		userRepository.save(user);
    		
    		return user;
    	}
    }
	
	@Override
    public List<User> getAllUsers() {
		return userRepository.findAll();
    }
	
	
	

}
