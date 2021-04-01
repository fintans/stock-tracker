package com.bigbank.stocktracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "stock")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonProperty("01. symbol")
	private String symbol;

	@JsonProperty("05. price")
	private float price;
	
	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override()
	public String toString() {
		return "stock:" + symbol;
	}

	public Stock() {
	}

	public Stock(String symbol, float price) {
		super();
		this.symbol = symbol;
		this.price = price;
	}

}
