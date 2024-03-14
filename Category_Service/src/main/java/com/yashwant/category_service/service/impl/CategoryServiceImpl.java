package com.yashwant.category_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yashwant.category_service.dtos.CategoryDto;
import com.yashwant.category_service.dtos.ProductDto;
import com.yashwant.category_service.entity.Category;
import com.yashwant.category_service.external.ProductService;
import com.yashwant.category_service.repository.CategoryRepo;
import com.yashwant.category_service.service.CategoryService;
import com.yashwant.category_service.util.ApiResponse;
import com.yashwant.category_service.util.CategoryResponse;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
		Category category = categoryRepo.findById(categoryId).get();
		
		// fetch product here 
		
		//http://localhost:8083/product/getProduct/1
		
		
//		//Product product = restTemplate.getForObject("http://localhost:8083/product/getProduct/1", Product.class);
//		Product product =  productService.getProduct(categoryId);
//		Product newProduct = product;
//		newProduct.setProductId("2");
//		Product product1 = productService.addProduct(newProduct);
//		List<Product>products = new ArrayList<>();
//		products.add(product);
//		category.setProducts(products);
		
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category>list = categoryRepo.findAll();
		List<CategoryDto>ans = new ArrayList<>();
		for(Category c : list)
		{
			CategoryDto categoryDto = mapper.map(c, CategoryDto.class);
			ans.add(categoryDto);
		}
		return ans;
	}

	@Override
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).get();
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category newCategory = categoryRepo.save(category);
		productService.updateProductByCategoryName(newCategory.getCategoryTitle(), categoryId);
		return mapper.map(newCategory, CategoryDto.class);
	}

	@Override
	public ApiResponse deleteCategory(String categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).get();
		categoryRepo.delete(category);
		productService.deleteProductByCategory(categoryId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Category deleted for given id : " + categoryId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public CategoryDto getByName(String categoryTitle) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findByCategoryTitle(categoryTitle);	
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryResponse getByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findByCategoryTitle(categoryName);	
		CategoryResponse response = new CategoryResponse();
		response.setCategoryId(category.getCategoryId());
		response.setCategoryTitle(category.getCategoryTitle());
		response.setCategoryDescription(category.getCategoryDescription());
		
		List<ProductDto>list = productService.getProductByCategoryName(categoryName);
		response.setProducts(list);
		return response;
	}
	

}
