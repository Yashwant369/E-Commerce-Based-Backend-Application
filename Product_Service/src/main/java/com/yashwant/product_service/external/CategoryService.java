package com.yashwant.product_service.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yashwant.product_service.dtos.CategoryDto;

@FeignClient(name = "CATEGORY-SERVICE")
public interface CategoryService {
	
	
	@GetMapping("category/getAllCategory")
	public List<CategoryDto>getAllCategory();
	
	@GetMapping("category/getByName/{name}")
	public CategoryDto getCategoryByName(@PathVariable String name);
	
	@PostMapping("category/addCategory")
	public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto);
	
	

}
