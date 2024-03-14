package com.yashwant.category_service.util;

import java.util.List;

import com.yashwant.category_service.dtos.ProductDto;

import lombok.Data;

@Data
public class CategoryResponse 
{
	
	private String categoryId;
	private String categoryTitle;
	private String categoryDescription;
	private List<ProductDto>products;
	
	

}
