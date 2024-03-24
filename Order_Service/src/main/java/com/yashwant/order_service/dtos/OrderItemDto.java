package com.yashwant.order_service.dtos;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private String orderItemId;
	private int quantity;
	private double totalPrice;
	private String productId;
	private OrderDto orderDto;

}
