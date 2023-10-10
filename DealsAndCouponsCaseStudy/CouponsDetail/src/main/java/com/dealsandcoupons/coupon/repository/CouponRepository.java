package com.dealsandcoupons.coupon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealsandcoupons.coupon.model.Coupon;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,String>
{

}
