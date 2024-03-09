package com.yashwant.product_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.product_service.entity.Product;
import com.yashwant.product_service.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController 
{
	@Autowired
	private ProductServiceImpl productService;
	
	@PostMapping("/addProduct")
	public Product addproduct(@RequestBody Product product)
	{
		return productService.addProduct(product);
	}
	
	@GetMapping("/getProduct/{productId}")
	public Product getproduct(@PathVariable String productId)
	{
		return productService.getproduct(productId);
	}

}
