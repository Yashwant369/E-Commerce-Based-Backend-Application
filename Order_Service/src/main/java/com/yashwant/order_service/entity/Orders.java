package com.yashwant.order_service.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Orders 
{
	@Id
	private String orderId;
	private String orderStatus;
	private String paymentStatus;
	private double paymentAmount;
	private String billingAddress;
	private String phoneNumber;
	private String billingName;
	private Date orderDate;
	private Date deliveredDate;
	private String userId;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem>orderItems = new ArrayList<>();
	

}
