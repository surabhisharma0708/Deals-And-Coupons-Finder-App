package com.dealsandcoupons.coupon.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.dealsandcoupons.coupon.model.Coupon;
import com.dealsandcoupons.coupon.repository.CouponRepository;

@Service
public class CouponService 
{
	public void addCoupon(String emailid, String couponCode) {

		List<String> couponList = new ArrayList<String>();

		CustomerDetail customerObj = customerRepo.findById(emailid).get(); 

		couponList = customerObj.getCoupons();

		if(couponList == null) {

			List<String> list = new ArrayList<String>();

			list.add(couponCode);

			customerObj.setCoupons(list);

			customerRepo.save(customerObj);

		}

		else {

			for(String coupon : couponList) {

				if(coupon.equals(couponCode)) {

					return;

				}

			}

			couponList.add(couponCode);

			customerObj.setCoupons(couponList);

			customerRepo.save(customerObj);

		}

	}

	public List<String> getAllCoupons(String emailid){

		return customerRepo.findById(emailid).get().getCoupons();

	}

	

}
