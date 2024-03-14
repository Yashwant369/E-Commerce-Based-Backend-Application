package com.yashwant.category_service.service;

import java.util.List;

import com.yashwant.category_service.dtos.CategoryDto;
import com.yashwant.category_service.util.ApiResponse;
import com.yashwant.category_service.util.CategoryResponse;

public interface CategoryService 
{
	public CategoryDto saveCategory(CategoryDto categoryDto);
	public CategoryDto getCategory(String categoryId);
	public List<CategoryDto>getAllCategory();
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);
	public ApiResponse deleteCategory(String categoryId);
	public CategoryDto getByName(String categoryName);
	public CategoryResponse getByCategoryName(String categoryName);

}
