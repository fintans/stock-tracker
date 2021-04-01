package com.bigbank.stocktracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bigbank.stocktracker.model.VantageOutPut;

@SpringBootTest
public class StockTrackerServiceTest {
	
	@Autowired
	StockTrackerService stockTrackerService;
	
	
	@Test
	public void shouldGetAlphaVantageQuote() {
		VantageOutPut outPut = stockTrackerService.getStock("PLUG").block();
		
		assertEquals("PLUG", outPut.getStock().getSymbol());
	
	}
	
	
}
