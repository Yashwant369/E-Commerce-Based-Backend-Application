package com.yashwant.category_service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yashwant.category_service.entity.Product;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService 
{
	@GetMapping("/product/getProduct/{productId}")
	public Product getProduct(@PathVariable String productId);
	
	@PostMapping("/product/addProduct")
	public Product addProduct(@RequestBody Product product);

}