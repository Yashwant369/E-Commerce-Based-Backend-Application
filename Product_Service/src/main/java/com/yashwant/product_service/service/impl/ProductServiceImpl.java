package com.yashwant.product_service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yashwant.product_service.dtos.ProductDto;
import com.yashwant.product_service.entity.Product;
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

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		String productId = UUID.randomUUID().toString();
		productDto.setProductId(productId);
		productDto.setProductAddedDate(new Date());
		Product product = mapper.map(productDto, Product.class);
		Product newProduct = productRepo.save(product);
		return mapper.map(newProduct, ProductDto.class);
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
		Product product 
		return null;
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
		return null;
	}

	@Override
	public List<ProductDto> getByLive(boolean live) {
		// TODO Auto-generated method stub
		
		return null;
	}
	

}
