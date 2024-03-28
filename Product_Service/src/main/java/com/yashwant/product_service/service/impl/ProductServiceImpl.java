package com.yashwant.product_service.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.yashwant.product_service.exception.BadRequestException;
import com.yashwant.product_service.exception.ResourceNotFoundException;
import com.yashwant.product_service.external.CategoryService;
import com.yashwant.product_service.repository.ProductRepo;
import com.yashwant.product_service.service.ProductService;
import com.yashwant.product_service.util.ApiResponse;
import com.yashwant.product_service.util.PageResponse;

import feign.FeignException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryService categoryService;
	
	private String path = "Images/Products/";

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
		Product product = productRepo.findById(productId).orElseThrow(()-> 
		new ResourceNotFoundException("Product not found for given product id : " + productId));
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public PageResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
			
		}
		else 
		{
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Product>page = productRepo.findAll(pageable);
		List<Product>list = page.getContent();
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("No product information found.");
		}
		List<ProductDto>ans = new ArrayList<>();
		for(Product p : list)
		{
			ProductDto productDto = mapper.map(p, ProductDto.class);
			ans.add(productDto);
		}
	    PageResponse<ProductDto>response = new PageResponse<>();
	    response.setContent(ans);
	    response.setLastPage(page.isLast());
	    response.setPageNumber(page.getNumber());
	    response.setPageSize(page.getSize());
	    response.setTotalPages(page.getTotalPages());
	    response.setTotalElememts(page.getTotalElements());
	    return response;
	}

	@Override
	public ProductDto updateProduct(String productId, ProductDto productDto) {
		
		
		// TODO Auto-generated method stub
		Product product = productRepo.findById(productId).orElseThrow(()-> 
		new ResourceNotFoundException("Product not found for given product id : " + productId));
		
		String name = productDto.getProductCategory();
		CategoryDto category = categoryService.getCategoryByName(name);
		product.setProductAddedDate(new Date());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductDiscountPrice(productDto.getProductDiscountPrice());
		product.setProductImage(productDto.getProductImage());
		product.setProductLive(productDto.getProductLive());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductQuantity(productDto.getProductQuantity());
		product.setProductTitle(productDto.getProductTitle());
		product.setCategoryId(category.getCategoryId());
		product.setProductCategory(productDto.getProductCategory());
		Product newProduct = productRepo.save(product);
		return mapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ApiResponse deleteProduct(String productId) {
		// TODO Auto-generated method stub
		Product product = productRepo.findById(productId).orElseThrow(()-> 
		new ResourceNotFoundException("Product not found for given product id : " + productId));
        String fullPath = path + product.getProductImage();
		Path paths = Paths.get(fullPath);
		try {
			Files.delete(paths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(product == null)
		{
			throw new ResourceNotFoundException("No product information found for given product name : "+name);
		}
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getByPrefixName(String name) {
		// TODO Auto-generated method stub
		List<Product>list = productRepo.getByName(name);
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("No product information found for given product name : "+name);
		}
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
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("No product information found.");
		}
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
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("No product information found for given category name : "+productCategory);
		}
		List<ProductDto>ans = new ArrayList<>();
		for(Product p : list)
		{
			ProductDto pDto = mapper.map(p,ProductDto.class);
			ans.add(pDto);
		}
		return ans;
	}
	

}
