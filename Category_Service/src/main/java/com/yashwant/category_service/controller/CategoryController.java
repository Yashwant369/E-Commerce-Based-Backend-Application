package com.yashwant.category_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.category_service.entity.Category;
import com.yashwant.category_service.service.impl.CategoryServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/category")
public class CategoryController 
{
	@Autowired
	private CategoryServiceImpl  categoryService;
	
	@PostMapping("/addCategory")
	public Category addCategory(@RequestBody Category category)
	{
		return categoryService.saveCategory(category);
		
	}
	@GetMapping("/getCategory/{categoryId}")
	@CircuitBreaker(name = "productServiceBreaker", fallbackMethod = "productServiceFallback")
	@RateLimiter(name = "categoryRateLimiter", fallbackMethod = "productServiceFallback")
	public Category getCategory(@PathVariable String categoryId)
	{
		return categoryService.getCategory(categoryId);
	}
	
	public Category productServiceFallback(String categoryId, Exception ex)
	{
		System.out.println(ex.getMessage());
		Category cat = new Category();
		cat.setCategoryId(categoryId);
		cat.setCategoryTitle("Dummy title");
		cat.setCategoryDescription("Dummy description");
		cat.setProducts(null);
		return cat;
	}

}
