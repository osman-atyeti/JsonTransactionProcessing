package com.osman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemsItem{

	@JsonProperty("item")
	private String item;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("item_subtotal")
	private double itemSubtotal;

	@JsonProperty("price")
	private double price;

	@JsonProperty("item_uuid")
	private String itemUuid;

	@JsonProperty("category")
	private String category;

	@JsonProperty("potential_discount")
	private double potentialDiscount;


}