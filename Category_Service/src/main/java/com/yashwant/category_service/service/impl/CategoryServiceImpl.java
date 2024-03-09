package com.yashwant.category_service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yashwant.category_service.entity.Category;
import com.yashwant.category_service.entity.Product;
import com.yashwant.category_service.external.ProductService;
import com.yashwant.category_service.repository.CategoryRepo;
import com.yashwant.category_service.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		Category newCategory = categoryRepo.save(category);
		return newCategory;
	}

	@Override
	public Category getCategory(String categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).get();
		
		// fetch product here 
		
		//http://localhost:8083/product/getProduct/1
		
		
		//Product product = restTemplate.getForObject("http://localhost:8083/product/getProduct/1", Product.class);
		Product product =  productService.getProduct(categoryId);
		Product newProduct = product;
		newProduct.setProductId("2");
		Product product1 = productService.addProduct(newProduct);
		List<Product>products = new ArrayList<>();
		products.add(product);
		category.setProducts(products);
		
		return category;
	}
	

}
