package com.bigbank.stocktracker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bigbank.stocktracker.model.Stock;
import com.bigbank.stocktracker.model.VantageOutPut;
import com.bigbank.stocktracker.service.StockTrackerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class StockTrackerController {
	
	Logger logger = LogManager.getLogger(StockTrackerController.class);
	
	@Autowired
	StockTrackerService stockTrackerService;
	
	/**
	 * Returns stock data based on user search
	 * @param stock name
	 * @return Mono containing stock data
	 */
	@ResponseBody
	@GetMapping("/stock/{symbol}") ResponseEntity<Mono<VantageOutPut>> getStock(@PathVariable(value = "symbol") String symbol) {
		Mono<VantageOutPut> stock = stockTrackerService.getStock(symbol);
		HttpStatus status = stock != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Mono<VantageOutPut>>(stock, status);
	}
}
