package com.bigbank.stocktracker.service;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(String userAlreadyExists) {
		super(userAlreadyExists);
	}
}
