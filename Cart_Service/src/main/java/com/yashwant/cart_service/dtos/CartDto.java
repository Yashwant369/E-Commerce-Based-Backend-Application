package com.yashwant.cart_service.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CartDto 
{
	
	private String cartId;
	private Date createdDate;
	private String userId;
	private List<CartItemDto>cartItem = new ArrayList<>();

}
