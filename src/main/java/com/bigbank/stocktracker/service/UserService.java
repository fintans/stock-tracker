package com.bigbank.stocktracker.service;

import java.util.List;
import java.util.Set;

import com.bigbank.stocktracker.model.Stock;
import com.bigbank.stocktracker.model.User;

public interface UserService {

	User register(User user);
	
	List<User> getAllUsers();
	
	List<Stock> addStockToFavourites(String symbol);
	
	List<Stock> removeStockFromFavourites(String symbol);
}
