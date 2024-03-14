package com.yashwant.category_service.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yashwant.category_service.dtos.ProductDto;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService 
{
	@GetMapping("product/getProduct/{productId}")
	public ProductDto getProduct(@PathVariable String productId);
	
	@PostMapping("product/addProduct")
	public ProductDto addProduct(@RequestBody ProductDto product);
	
	
	@DeleteMapping("product/deleteProductByCategory/{categoryId}")
	public void deleteProductByCategory(@PathVariable String categoryId);
	
	@PutMapping("product/updateProductByCategory/{categoryName}/{categoryId}")
	public void updateProductByCategoryName(@PathVariable String categoryName, @PathVariable String categoryId);
	
	
	@GetMapping("product/getProductByCategoryName/{categoryName}")
	public List<ProductDto>getProductByCategoryName(@PathVariable String categoryName);
	
	
	

}
