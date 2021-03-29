package com.bigbank.stocktracker.service;

import java.util.List;

import com.bigbank.stocktracker.model.User;

public interface UserService {

	User register(User user);
	
	List<User> getAllUsers();
}
