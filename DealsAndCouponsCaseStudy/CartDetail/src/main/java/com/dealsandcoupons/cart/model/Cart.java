package com.dealsandcoupons.cart.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart
{
	@Id
	private String customerId;
	private String itemId;
	private int quantity;
	private HashMap<String,Integer> itemDetails;
	private double cartTotal;
}
