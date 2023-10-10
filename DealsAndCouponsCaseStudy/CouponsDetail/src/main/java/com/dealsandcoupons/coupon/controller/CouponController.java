package com.dealsandcoupons.coupon.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.dealsandcoupons.coupon.model.Coupon;
import com.dealsandcoupons.coupon.repository.CouponRepository;
import com.dealsandcoupons.coupon.service.CouponService;

@RequestMapping("/couponService")
@RestController
@CrossOrigin("http://localhost:4200")
public class CouponController 
{
	@Autowired
	private RestTemplate restTemplate;
	@Autowired 
	private CouponService service;
	@Autowired
	private CouponRepository repo;
	
	@PostMapping("/addCoupon")
	public String addCoupon(@RequestParam("itemName") String itemName,@RequestParam("file") MultipartFile file,@RequestParam("cashBackPoints") int cashBackPoints) throws IOException
	{
		return service.addCoupon(itemName,file,cashBackPoints);
	}
	@GetMapping("/getCoupon")
	public Coupon getCoupon()
	{
		return service.getCoupon();
	}
	private String url = "http://localhost:8084/itemService";
	@GetMapping("/fetch")
	public void fetchAndStore()
	{
		ResponseEntity<Coupon[]> response = restTemplate.getForEntity(url+"/getAllItem", Coupon[].class);
		Coupon[] coupon =response.getBody();
		for(Coupon c: coupon)
		{
			repo.save(c);
		}
	}
}
