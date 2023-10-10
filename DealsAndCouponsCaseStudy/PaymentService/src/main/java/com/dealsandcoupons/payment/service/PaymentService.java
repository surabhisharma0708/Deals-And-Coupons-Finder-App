package com.dealsandcoupons.payment.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dealsandcoupons.payment.model.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService 
{

private static final String KEY = "rzp_test_chsz62ATUO203u";
private static final String KEY_SECRET = "7d8dSRZ1BFsRNYiRBys5jQkQ";
private static final String CURRENCY = "INR";

public Payment createTransaction(double amount) {
try {
JSONObject jsonObject = new JSONObject();
jsonObject.put("amount", (amount * 100));
jsonObject.put("currency", CURRENCY);


RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
Order order = razorpayClient.orders.create(jsonObject);
// System.out.println(order);
return prepareTransactionDetails(order);
}
catch(Exception e) {
System.out.println(e.getMessage());
}
return null;
}

private Payment prepareTransactionDetails(Order order) {
String orderId = order.get("id");
String currency = order.get("currency");
Integer amount = order.get("amount");

Payment payment = new Payment(orderId, currency, amount, KEY);
return payment;
}
}
