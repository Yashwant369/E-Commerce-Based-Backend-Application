package com.yashwant.product_service.service;

import java.util.List;

import com.yashwant.product_service.dtos.ProductDto;
import com.yashwant.product_service.util.ApiResponse;
import com.yashwant.product_service.util.PageResponse;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto productDto);
	public ProductDto getProduct(String productId);
	public PageResponse<ProductDto>getAllProduct(int pageNumber,int pageSize, String sortBy, String sortDir);
	public ProductDto updateProduct(String productId, ProductDto productDto);
	public ApiResponse deleteProduct(String productId);
	public ProductDto getByName(String name);
	public List<ProductDto>getByPrefixName(String name);
	public List<ProductDto>getByLive(String live);
	public void deleteProductByCategory(String categoryId);
	public void updateCategory(String productCategory,String categoryId);
	public List<ProductDto>getproductByCategoryName(String productCategory);
	

}
