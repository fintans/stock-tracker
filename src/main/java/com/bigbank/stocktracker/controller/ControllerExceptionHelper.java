package com.bigbank.stocktracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bigbank.stocktracker.service.UserAlreadyExistsException;
import com.bigbank.stocktracker.service.UserNotExistsException;

@ControllerAdvice
public class ControllerExceptionHelper {
	
	@ExceptionHandler(value = {UserAlreadyExistsException.class})
	protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {UserNotExistsException.class})
	protected ResponseEntity<Object> handleUserNotExistsException(UserNotExistsException e) {
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}