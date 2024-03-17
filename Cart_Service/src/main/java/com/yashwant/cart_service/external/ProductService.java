package com.yashwant.cart_service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yashwant.cart_service.dtos.ProductDto;


@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {
	
	@GetMapping("product/getById/{productId}")
	public ProductDto getByProductId(@PathVariable String productId);

}
