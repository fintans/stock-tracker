package com.bigbank.stocktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VantageOutPut {

	@JsonProperty("Global Quote")
	Stock stock;
	
	
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public VantageOutPut(Stock stock) {
		super();
		this.stock = stock;
	}

	public VantageOutPut() {

	}
}
