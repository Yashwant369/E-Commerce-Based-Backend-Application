package com.yashwant.product_service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yashwant.product_service.dtos.CategoryDto;
import com.yashwant.product_service.dtos.ProductDto;
import com.yashwant.product_service.entity.Product;
import com.yashwant.product_service.external.CategoryService;
import com.yashwant.product_service.repository.ProductRepo;
import com.yashwant.product_service.service.ProductService;
import com.yashwant.product_service.util.ApiResponse;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		String productId = UUID.randomUUID().toString();
		productDto.setProductId(productId);
		productDto.setProductAddedDate(new Date());
		String name = productDto.getProductCategory();
		CategoryDto category = categoryService.getCategoryByName(name);
		Product product = mapper.map(productDto, Product.class);
		product.setCategoryId(category.getCategoryId());
		Product newProduct = productRepo.save(product);
		ProductDto newProductDto = mapper.map(newProduct, ProductDto.class);
		return newProductDto;
		
	}

	@Override
	public ProductDto getProduct(String productId) {
		// TODO Auto-generated method stub
		Product product = productRepo.findById(productId).get();
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product>list = productRepo.findAll();
		List<ProductDto>ans = new ArrayList<>();
		for(Product p : list)
		{
			ProductDto productDto = mapper.map(p, ProductDto.class);
			ans.add(productDto);
		}
		return ans;
	}

	@Override
	public ProductDto updateProduct(String productId, ProductDto productDto) {
		// TODO Auto-generated method stub
		Product product = productRepo.findById(productId).get();
		product.setProductAddedDate(new Date());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductDiscountPrice(productDto.getProductDiscountPrice());
		product.setProductImage(productDto.getProductImage());
		product.setProductLive(productDto.getProductLive());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductQuantity(productDto.getProductQuantity());
		product.setProductTitle(productDto.getProductTitle());
		String name = productDto.getProductCategory();
		CategoryDto category = categoryService.getCategoryByName(name);
		product.setCategoryId(category.getCategoryId());
		product.setProductCategory(productDto.getProductCategory());
		Product newProduct = productRepo.save(product);
		return mapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ApiResponse deleteProduct(String productId) {
		// TODO Auto-generated method stub
		Product product = productRepo.findById(productId).get();
		productRepo.delete(product);
		ApiResponse response = new ApiResponse();
		response.setMessage("Product deleted for given id : " + productId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public ProductDto getByName(String name) {
		// TODO Auto-generated method stub
		
		Product product = productRepo.findByProductTitle(name);
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getByPrefixName(String name) {
		// TODO Auto-generated method stub
		List<Product>list = productRepo.getByName(name);
		List<ProductDto>ans = new ArrayList<>();
		for(Product p : list)
		{
			ProductDto productDto = mapper.map(p, ProductDto.class);
			ans.add(productDto);
		}
		return ans;
	}

	@Override
	public List<ProductDto> getByLive(String live) {
		// TODO Auto-generated method stub
		List<Product>list = productRepo.findByProductLive(live);
		List<ProductDto>ans = new ArrayList<>();
		for(Product p : list)
		{
			ProductDto productDto = mapper.map(p, ProductDto.class);
			ans.add(productDto);
		}
		return ans;
		
	}

	@Override
	@Transactional
	public void deleteProductByCategory(String categoryId) {
		// TODO Auto-generated method stub
		
		productRepo.deleteProductByCategoryId(categoryId);
		
	}

	@Override
	@Transactional
	public void updateCategory(String productCategory, String categoryId) {
		// TODO Auto-generated method stub
		productRepo.updateProductByCategoryName(productCategory, categoryId);
		
	}

	@Override
	public List<ProductDto> getproductByCategoryName(String productCategory) {
		// TODO Auto-generated method stub
		List<Product>list = productRepo.findProductCategory(productCategory);
		List<ProductDto>ans = new ArrayList<>();
		for(Product p : list)
		{
			ProductDto pDto = mapper.map(p,ProductDto.class);
			ans.add(pDto);
		}
		return ans;
	}
	

}
