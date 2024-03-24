package com.yashwant.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderItem {
	
	@Id
	private String orderItemId;
	private int quantity;
	private double totalPrice;
	private String productId;
	
	@ManyToOne
	private Orders order;

}
