package com.yashwant.cart_service.util;

import lombok.Data;

@Data
public class AddItemRequest {
	
	private String productId;
	private int quantity;

}
