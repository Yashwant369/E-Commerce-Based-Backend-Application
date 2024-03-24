package com.yashwant.order_service.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
	
	private String orderId;
	private String orderStatus = "Pending";
	private String paymentStatus = "Pending";
	private double paymentAmount;
	private String billingAddress;
	private String phoneNumber;
	private String billingName;
	private Date orderDate = new Date();
	private Date deliveredDate;
	private String userId;
	private List<OrderItemDto>orderItems = new ArrayList<>();

}
