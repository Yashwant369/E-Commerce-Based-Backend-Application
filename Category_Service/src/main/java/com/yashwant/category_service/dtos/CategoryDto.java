package com.yashwant.category_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto 
{
	
	private String categoryId;
	@NotBlank(message = "Please enter category title")
	@Size(min = 2, max = 256 , message = "Category title must be between 2 and 256 characters")
	private String categoryTitle;
	@NotBlank(message = "Please enter category description")
	@Size(min = 2, max = 256 , message = "Category description must be between 2 and 256 characters")
	private String categoryDescription;

}
