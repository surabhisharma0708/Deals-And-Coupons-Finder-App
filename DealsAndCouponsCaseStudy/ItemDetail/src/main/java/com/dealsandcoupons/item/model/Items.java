package com.dealsandcoupons.item.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="items")
public class Items 
{
	@Id
	private String itemId;
	private String itemName;
	private double price;
	private String couponCode;
	private int couponDiscount;
	private byte[] image;
	private int rating;
	private String description;
	private int totalAvailableQuantity;
	private int discountOnItem;
	private String category;
}
