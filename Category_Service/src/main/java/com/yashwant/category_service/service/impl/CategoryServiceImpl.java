package com.yashwant.category_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.yashwant.category_service.dtos.CategoryDto;
import com.yashwant.category_service.dtos.ProductDto;
import com.yashwant.category_service.entity.Category;
import com.yashwant.category_service.exception.ResourceNotFoundException;
import com.yashwant.category_service.external.ProductService;
import com.yashwant.category_service.repository.CategoryRepo;
import com.yashwant.category_service.service.CategoryService;
import com.yashwant.category_service.util.ApiResponse;
import com.yashwant.category_service.util.CategoryResponse;
import com.yashwant.category_service.util.PageResponse;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		String categoryId = UUID.randomUUID().toString();
		categoryDto.setCategoryId(categoryId);
		Category category = mapper.map(categoryDto, Category.class);
		Category newCategory = categoryRepo.save(category);
		return mapper.map(newCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(String categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException
				("Category information not found for given category id : " + categoryId));		
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public PageResponse<CategoryDto>getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = null;
		if(sortDir.equals("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}
		else if(sortDir.equals("desc"))
		{
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Category>page = categoryRepo.findAll(pageable);
		List<Category>list = page.getContent();
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("No category information found.");
		}
		List<CategoryDto>ans = new ArrayList<>();
		for(Category c : list)
		{
			CategoryDto categoryDto = mapper.map(c, CategoryDto.class);
			ans.add(categoryDto);
		}
		PageResponse<CategoryDto>response = new PageResponse<>();
		response.setContent(ans);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastpage(page.isLast());
		return response;
		
	}

	@Override
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException
				("Resource not found for given category id : " + categoryId));	
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category newCategory = categoryRepo.save(category);
		productService.updateProductByCategoryName(newCategory.getCategoryTitle(), categoryId);
		return mapper.map(newCategory, CategoryDto.class);
	}

	@Override
	public ApiResponse deleteCategory(String categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException
				("Category information not found for given category id : " + categoryId));	
		categoryRepo.delete(category);
		productService.deleteProductByCategory(categoryId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Category deleted for given id : " + categoryId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	

	@Override
	public CategoryResponse getByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findByCategoryTitle(categoryName);	
		if(category == null)
		{
			throw new ResourceNotFoundException("Category information not found for given category name : " + categoryName);
		}
		CategoryResponse response = new CategoryResponse();
		response.setCategoryId(category.getCategoryId());
		response.setCategoryTitle(category.getCategoryTitle());
		response.setCategoryDescription(category.getCategoryDescription());
		
		List<ProductDto>list = productService.getProductByCategoryName(categoryName);
		response.setProducts(list);
		return response;
	}
	

}
