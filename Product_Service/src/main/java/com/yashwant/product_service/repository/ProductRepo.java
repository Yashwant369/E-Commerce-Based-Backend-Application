package com.yashwant.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.product_service.entity.Product;

public interface ProductRepo extends JpaRepository<Product,String>
{

	Product findByProductTitle(String name);
	
	

}
