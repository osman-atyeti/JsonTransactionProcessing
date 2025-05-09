package com.osman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Shipping{

	@JsonProperty("carrier")
	private String carrier;

	@JsonProperty("delivery_estimate")
	private String deliveryEstimate;

	@JsonProperty("status")
	private String status;


}