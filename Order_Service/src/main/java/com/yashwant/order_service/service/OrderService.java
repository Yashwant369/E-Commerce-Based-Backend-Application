package com.yashwant.order_service.service;

import java.util.List;

import com.yashwant.order_service.dtos.OrderDto;
import com.yashwant.order_service.utils.OrderRequest;

public interface OrderService {
	
	OrderDto createOrder(OrderRequest request);
	void removeOrder(String orderId);
	List<OrderDto>getUserOrder(String userId);
	List<OrderDto>getAllOrder();
	OrderDto updateOrder(String orderId);
	
}
