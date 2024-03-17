package com.yashwant.cart_service.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cart 
{
	@Id
	private String cartId;
	private Date createdDate;
	private String userId;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem>cartItem = new ArrayList<>();
	

}
