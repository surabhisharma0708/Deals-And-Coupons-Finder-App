package com.dealsandcoupons.coupon.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="coupons")
public class Coupon 
{
	private String itemName;
	private byte[] image;
	private int cashBackPoints;
}
