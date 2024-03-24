package com.yashwant.category_service.service;

import java.util.List;

import com.yashwant.category_service.dtos.CategoryDto;
import com.yashwant.category_service.util.ApiResponse;
import com.yashwant.category_service.util.CategoryResponse;
import com.yashwant.category_service.util.PageResponse;

public interface CategoryService 
{
	public CategoryDto saveCategory(CategoryDto categoryDto);
	public CategoryDto getCategory(String categoryId);
	public PageResponse<CategoryDto>getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);
	public ApiResponse deleteCategory(String categoryId);
	public CategoryResponse getByCategoryName(String categoryName);

}
