package com.bigbank.stocktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VantageOutPut {

	@JsonProperty("Global Quote")
	Stock stock;

	public VantageOutPut() {

	}
}
