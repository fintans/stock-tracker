package com.bigbank.stocktracker.service;

import static com.bigbank.stocktracker.util.ErrorReason.USER_NOT_EXISTS;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	Logger logger = LogManager.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	public CustomUserDetailsService() {
		super();
	}
	
	/**
	 * Locates current user from db for authentication 
	 * @param username current user
	 * @return UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		logger.info("loadUserByUsername()");
		Optional<User> user = userRepository.findByUserName(username);
		if (user.isPresent()) {
			return org.springframework.security.core.userdetails.User.withUsername(user.get().getUsername())
					.password(user.get().getPassword()).authorities("USER").build();
		} else {
			throw new UserNotExistsException(USER_NOT_EXISTS.toString());
		}
	}

}