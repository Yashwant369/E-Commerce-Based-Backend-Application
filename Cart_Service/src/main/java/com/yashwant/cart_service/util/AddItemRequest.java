package com.yashwant.cart_service.util;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
public class AddItemRequest {
	
	@NotBlank(message = "Please enter product id.")
	private String productId;
	@Min(value = 1, message = "Please enter product quantity greater than 0")
	private int quantity;

}
