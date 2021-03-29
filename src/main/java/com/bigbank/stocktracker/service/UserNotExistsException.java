package com.bigbank.stocktracker.service;

public class UserNotExistsException extends RuntimeException {
	
	public UserNotExistsException(String userNotExists) {
		super(userNotExists);
	}

}
