package com.bigbank.stocktracker.service;

import static com.bigbank.stocktracker.util.ErrorReason.USER_ALREADY_EXISTS;
import static com.bigbank.stocktracker.util.ErrorReason.USER_NOT_EXISTS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bigbank.stocktracker.model.Stock;
import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.model.VantageOutPut;
import com.bigbank.stocktracker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	StockTrackerService stockTrackerService;
	
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
	public List<Stock> addStockToFavourites(String symbol) {
		VantageOutPut vantageOutPut = stockTrackerService.getStock(symbol).block();
		Optional<User> userOptional = userRepository.findByUserName(this.getPrincipalUser());
		
    	if(userOptional.isPresent()) {
    		User user = userOptional.get();
    		List<Stock> stockFavourites = user.getStockFavourites();
    		if(!stockFavourites.stream().anyMatch(stock -> stock.getSymbol().equalsIgnoreCase(symbol))) {
        		stockFavourites.add(vantageOutPut.getStock());
        		user.setStockFavourites(stockFavourites);
        		userRepository.save(user);
    		}
    		return stockFavourites;
    	} else {
    		throw new UserAlreadyExistsException(USER_NOT_EXISTS.toString());
    	}
	}
	
	@Override
	public List<Stock> removeStockFromFavourites(String symbol) {
		Optional<User> userOptional = userRepository.findByUserName(this.getPrincipalUser());
		
    	if(userOptional.isPresent()) {
    		User user = userOptional.get();
    		List<Stock> stockFavourites = user.getStockFavourites();
    		
    		stockFavourites.removeIf(stock -> stock.getSymbol().equalsIgnoreCase(symbol));
    	//	user.setStockFavourites(stockFavourites);
    		userRepository.save(user);
    		
    		return stockFavourites;
    	} else {
    		throw new UserAlreadyExistsException(USER_NOT_EXISTS.toString());
    	}
	}
	
	private String getPrincipalUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@Override
    public List<User> getAllUsers() {
		return userRepository.findAll();
    }

	
	
	

}
