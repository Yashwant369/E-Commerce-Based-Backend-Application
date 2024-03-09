package com.yashwant.user_service.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Cart {
	
	
	private String cartId;	
	private Date createdDate;
	private User user;
	

}
