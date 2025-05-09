package com.osman.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transaction{

	@JsonProperty("Transaction")
	private List<TransactionItem> transaction;

}