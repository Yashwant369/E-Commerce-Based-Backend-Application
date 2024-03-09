package com.yashwant.user_service.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Orders {
	
	
	private String orderId;
	private String orderStatus;
	private String paymentStatus;
	private double orderAmount;
	private String orderAddress;
	private String phoneNumber;
	private String billingName;
	private Date orderDate;
	private Date deliveryDate;
	private User user;
	
}
