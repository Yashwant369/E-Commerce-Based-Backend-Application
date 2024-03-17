package com.yashwant.cart_service.service;

import com.yashwant.cart_service.dtos.CartDto;
import com.yashwant.cart_service.util.AddItemRequest;

public interface CartService 
{
	public CartDto addItem(String userId, AddItemRequest request);
	public void removeItem(String cartItemId);
	public void clearCart(String userId);
	public CartDto getCartByUser(String userId);
	

}
