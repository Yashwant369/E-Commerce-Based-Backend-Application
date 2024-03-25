package com.yashwant.product_service.dtos;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
	
	private String productId;
	@NotBlank(message = "Please enter product title.")
	@Size(min = 2, max = 256 , message = "Product title must be in between 2 and 256 characters.")
	private String productTitle;
	@NotBlank(message = "Please enter product description.")
	@Size(min = 2, max = 256, message = "Product description must be in between 2 and 256 characters.")
	private String productDescription;
	
	@Min(value = 1, message="Only integer or double values are allowed.")
	private double productPrice;
	
	@Min(value = 0, message="Only integer or double values are allowed.")
	private double productDiscountPrice;
	
	@Min(value = 1, message = "Value must be a positive integer.")
	private Integer productQuantity;
	private Date productAddedDate;
	@NotBlank(message = "Please enter product live status.")
	@Size(min = 2, max = 256, message = "Product live status must be in 2 and 256 characters.")
	private String productLive;
	@NotBlank(message = "Please enter product category.")
	@Size(min = 2, max = 256, message = "Product category must be in 2 and 256 characters.")
	private String productCategory;
	private String productImage;
	

}
