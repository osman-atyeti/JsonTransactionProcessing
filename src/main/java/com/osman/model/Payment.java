package com.osman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payment{

	@JsonProperty("amount")
	private double amount;

	@JsonProperty("method")
	private String method;

	@JsonProperty("currency")
	private String currency;



}