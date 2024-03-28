package com.yashwant.category_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.category_service.dtos.CategoryDto;
import com.yashwant.category_service.service.impl.CategoryServiceImpl;
import com.yashwant.category_service.util.ApiResponse;
import com.yashwant.category_service.util.CategoryResponse;
import com.yashwant.category_service.util.PageResponse;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController 
{
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	
	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto>saveCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto category = categoryService.saveCategory(categoryDto);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}	
	@PutMapping("/updateCategory/{categoryId}")
	@RateLimiter(name = "categoryRateLimiter",fallbackMethod = "categoryFallBack")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable String categoryId)
	{
		CategoryDto category = categoryService.updateCategory(categoryId, categoryDto);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	@DeleteMapping("/deleteCategory/{categoryId}")
	//@RateLimiter(name = "categoryRateLimiter",fallbackMethod = "categoryFallBack")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable String categoryId)
	{
		ApiResponse response = categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/getAllCategory")
	@RateLimiter(name = "categoryRateLimiter",fallbackMethod = "categoryFallBack")
	public ResponseEntity<PageResponse<CategoryDto>>getAllCategory(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "categoryTitle",required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc", required = false)String sortDir)
	{
		
		PageResponse<CategoryDto>response = categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/getById/{categoryId}")
	@RateLimiter(name = "categoryRateLimiter", fallbackMethod = "categoryFallBack")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable String categoryId)
	{
		CategoryDto category = categoryService.getCategory(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@GetMapping("/getByName/{name}")
	@RateLimiter(name = "categoryRateLimiter",fallbackMethod = "categoryFallBack")
	public ResponseEntity<CategoryResponse> getByName(@PathVariable String name)
	{
		CategoryResponse response = categoryService.getByCategoryName(name);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	public ResponseEntity<ApiResponse>categoryFallBack(Exception ex)
	{
		ApiResponse response = new ApiResponse();
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setSuccess(false);
		response.setMessage("RequestNotPermitted");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/getByCategoryName/{name}")
	public CategoryDto getCategoryByName(@PathVariable String name)
	{
		CategoryDto category = categoryService.getByCategoryName1(name);
		return category;
	}

}
