package com.yashwant.product_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yashwant.product_service.entity.Product;
import com.yashwant.product_service.repository.ProductRepo;

@Service
public class ProductServiceImpl 
{
	@Autowired
	private ProductRepo productRepo;
	
	public Product addProduct(Product product)
	{
		Product newProduct = productRepo.save(product);
		return newProduct;
	}
	
	public Product getproduct(String productId)
	{
		Product product = productRepo.findById(productId).get();
		return product;
	}

}
