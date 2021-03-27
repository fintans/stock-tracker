package com.bigbank.stocktracker.service;

import com.bigbank.stocktracker.model.VantageOutPut;

import reactor.core.publisher.Mono;

public interface StockTrackerService {
	
	Mono<VantageOutPut> getStock(String symbol);
	
}
