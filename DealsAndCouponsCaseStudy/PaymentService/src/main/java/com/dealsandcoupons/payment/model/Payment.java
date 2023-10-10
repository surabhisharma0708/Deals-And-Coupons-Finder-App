package com.dealsandcoupons.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment 
{

private String orderId;
private String currency;
private Integer amount;
private String key;
}
