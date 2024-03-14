package com.yashwant.category_service.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.category_service.dtos.CategoryDto;
import com.yashwant.category_service.service.impl.CategoryServiceImpl;
import com.yashwant.category_service.util.ApiResponse;
import com.yashwant.category_service.util.CategoryResponse;

@RestController
@RequestMapping("/category")
public class CategoryController 
{
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	
	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto>saveCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto category = categoryService.saveCategory(categoryDto);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto>updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId)
	{
		CategoryDto category = categoryService.updateCategory(categoryId, categoryDto);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable String categoryId)
	{
		ApiResponse response = categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>>getAllCategory()
	{
		List<CategoryDto>list = categoryService.getAllCategory();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/getById/{categoryId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable String categoryId)
	{
		CategoryDto category = categoryService.getCategory(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	@GetMapping("/getByName/{name}")
	public ResponseEntity<CategoryResponse> getByName(@PathVariable String name)
	{
		CategoryResponse response = categoryService.getByCategoryName(name);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	

}
