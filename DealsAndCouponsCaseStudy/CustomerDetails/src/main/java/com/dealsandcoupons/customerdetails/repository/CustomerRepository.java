package com.dealsandcoupons.customerdetails.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealsandcoupons.customerdetails.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String>
{

	Optional<Customer> findByName(String name);

}
