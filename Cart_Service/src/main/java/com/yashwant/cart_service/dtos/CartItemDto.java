package com.yashwant.cart_service.dtos;

import lombok.Data;

@Data
public class CartItemDto 
{
	private String cartItemId;
	private String productId;
	private int productQuantity;
	private double totalPrice;

}
