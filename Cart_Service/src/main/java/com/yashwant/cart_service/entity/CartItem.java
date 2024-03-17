package com.yashwant.cart_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
	
	@Id
	private String cartItemId;
	private String productId;
	private int productQuantity;
	private double totalPrice;
	@ManyToOne
	private Cart cart;
	

}
