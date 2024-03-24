package com.yashwant.order_service.externals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.yashwant.order_service.dtos.CartDto;
import com.yashwant.order_service.utils.ApiResponse;



@FeignClient(name = "CART-SERVICE")
public interface CartService {
	
	
	@GetMapping("cart/getCart/{userId}")
	public CartDto getCart(@PathVariable String userId);
	
	@GetMapping("cart/clearCart/{userId}")
	public ApiResponse clearCart(@PathVariable String userId);

}
