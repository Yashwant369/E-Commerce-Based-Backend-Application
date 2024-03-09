package com.yashwant.product_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Product 
{
	@Id
	private String productId;
	private String productName;
	private String productCategory;
	
//	@Transient
//	private Category category;
	
	

}
