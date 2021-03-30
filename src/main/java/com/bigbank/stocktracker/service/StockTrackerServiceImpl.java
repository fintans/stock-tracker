package com.bigbank.stocktracker.service;

import static com.bigbank.stocktracker.util.ErrorReason.USER_ALREADY_EXISTS;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebInputException;

import com.bigbank.stocktracker.model.Stock;
import com.bigbank.stocktracker.model.VantageOutPut;

import reactor.core.publisher.Mono;

@Service
public class StockTrackerServiceImpl implements StockTrackerService {

	Logger logger = LogManager.getLogger(StockTrackerServiceImpl.class);

	@Value("${api.key}")
	private String apiKey;

	private final String quoteUrl = "/query?function=GLOBAL_QUOTE&symbol={symbol}&apikey={apiKey}";
	
	WebClient client = WebClient.builder().baseUrl("https://www.alphavantage.co").build();

	@Override
	public Mono<VantageOutPut> getStock(String symbol) {		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		logger.info("here" + currentPrincipalName);
		
		return this.client.get().uri(this.quoteUrl, symbol, this.apiKey)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(VantageOutPut.class)
			.switchIfEmpty(Mono.error(new ServerWebInputException("Request body cannot be empty.")))
			.log();
	}
	
//	public List<Stock> addStockToFavourites(String symbol) {
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String user = authentication.getName();
//		
//    	if(userRepository.findByUserName(user).isPresent()) {
//    		throw new UserAlreadyExistsException(USER_ALREADY_EXISTS.toString());
//    	} else {
//    		user.setPassword(passwordEncoder.encode(user.getPassword()));
//    		userRepository.save(user);
//    		
//    		return user;
//    	}
//		
//		
//		VantageOutPut vantageOutPut = this.getStock(symbol).block();
//		
//		
//
//	}
}
