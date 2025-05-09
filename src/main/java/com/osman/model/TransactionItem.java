package com.osman.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionItem{

	@JsonProperty("transaction_id")
	private String transactionId;

	@JsonProperty("final_amount_payable")
	private int finalAmountPayable;

	@JsonProperty("shipping")
	private Shipping shipping;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("total_discount_applied")
	private int totalDiscountApplied;

	@JsonProperty("payment")
	private Payment payment;

	@JsonProperty("location")
	private String location;

	@JsonProperty("total_before_discount")
	private int totalBeforeDiscount;

	@JsonProperty("items")
	private List<ItemsItem> items;

	@JsonProperty("timestamp")
	private String timestamp;


}