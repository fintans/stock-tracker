package com.bigbank.stocktracker.util;

public enum ErrorReason {
	
	USER_ALREADY_EXISTS("User already Exsists"),
	USER_NOT_EXISTS("User does not exist");
	
	private final String description;

	private ErrorReason(String description) {
		this.description = description;
	}
	
	  @Override
	  public String toString() {
	    return description;
	  }
	
	

}