package com.bigbank.stocktracker.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebInputException;

import com.bigbank.stocktracker.model.VantageOutPut;

import reactor.core.publisher.Mono;

@Service
public class StockTrackerServiceImpl implements StockTrackerService {

	Logger logger = LogManager.getLogger(StockTrackerServiceImpl.class);

	@Value("${api.key}")
	private String apiKey;

	private final String quoteUrl = "/query?function=GLOBAL_QUOTE&symbol={symbol}&apikey={apiKey}";
	
	WebClient client = WebClient.builder().baseUrl("https://www.alphavantage.co").build();
	
	/**
	 * Returns stock data based on response from AlphaVantageClient
	 * @param stock name
	 * @return Mono containing stock data
	 */
	@Override
	public Mono<VantageOutPut> getStock(String symbol) {		
		return this.client.get().uri(this.quoteUrl, symbol, this.apiKey)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(VantageOutPut.class)
			.switchIfEmpty(Mono.error(new ServerWebInputException("Request body cannot be empty.")))
			.log();
	}
}
