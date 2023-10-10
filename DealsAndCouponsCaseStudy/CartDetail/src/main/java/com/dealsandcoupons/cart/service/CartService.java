package com.dealsandcoupons.cart.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealsandcoupons.cart.model.Cart;
import com.dealsandcoupons.cart.repository.CartRepository;

@Service
public class CartService 
{
	@Autowired
	private CartRepository repo;

	public void addItemInCart(Cart cart) 
	{
		// TODO Auto-generated method stub
		
		Optional<Cart> obj = repo.findById(cart.getCustomerId());
		if(obj.isEmpty())
		{
			HashMap<String,Integer> map = new HashMap<>();
			map.put(cart.getItemId(),1);
			cart.setItemDetails(map);
			repo.save(cart);
		}
		else
		{
			HashMap<String,Integer> map = obj.get().getItemDetails();
			if(map.containsKey(cart.getItemId()))
			{
				map.put(cart.getItemId(), obj.get().getItemDetails().get(cart.getItemId())+1);
			}
			else
			{
				map.put(cart.getItemId(),1);
			}	
			cart.setItemDetails(map);
			repo.save(cart);
		}
	}
	public void addCustomer(String customerId) {
		Cart cartObj = new Cart();
		cartObj.setCustomerId(customerId);
		cartObj.setItemDetails(new HashMap<String, Integer>());
		repo.save(cartObj);
	}
	public HashMap<String,Integer> getItemFromCart(String customerId) 
	{
		// TODO Auto-generated method stub
		Optional<Cart> cart = repo.findById(customerId);
		return cart.get().getItemDetails();
	}

	
	public void deleteItemFromCart(String customerId, String itemId) 
	{
		// TODO Auto-generated method stub
		Optional<Cart> cart = repo.findById(customerId);
		 HashMap<String, Integer> map =cart.get().getItemDetails();
		if(map.get(itemId)==1)
		{
			map.remove(itemId);
		}
		else if(map.get(itemId)>1)
		{
			map.put(itemId, map.get(itemId)-1);
		}
	}
}
