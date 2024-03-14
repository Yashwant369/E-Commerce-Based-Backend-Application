package com.yashwant.product_service.entity;

import java.util.Date;

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
	private String productTitle;
	private String productDescription;
	private double productPrice;
	private double productDiscountPrice;
	private Integer productQuantity;
	private Date productAddedDate;
	private String productLive;
	private String productCategory;
	private String productImage;
	private String categoryId;

}
