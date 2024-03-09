package com.yashwant.product_service.service;

import com.yashwant.product_service.entity.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	public Product getProduct(String productId);

}
