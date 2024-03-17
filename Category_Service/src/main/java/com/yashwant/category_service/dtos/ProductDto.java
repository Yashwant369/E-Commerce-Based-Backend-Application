package com.yashwant.category_service.dtos;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto 
{
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