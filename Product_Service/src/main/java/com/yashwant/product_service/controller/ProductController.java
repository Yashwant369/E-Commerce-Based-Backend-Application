package com.yashwant.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.product_service.dtos.CategoryDto;
import com.yashwant.product_service.dtos.ProductDto;
import com.yashwant.product_service.external.CategoryService;
import com.yashwant.product_service.service.impl.ProductServiceImpl;
import com.yashwant.product_service.util.ApiResponse;
import com.yashwant.product_service.util.PageResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController 
{
	@Autowired
	private ProductServiceImpl productService;
	
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductDto>saveProduct(@Valid @RequestBody ProductDto productDto)
	{
		ProductDto product = productService.addProduct(productDto);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<ProductDto>updateProduct(@PathVariable String productId, @Valid @RequestBody ProductDto productDto)
	{
		ProductDto product = productService.updateProduct(productId, productDto);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<ApiResponse>deleteProduct(@PathVariable String productId)
	{
		ApiResponse response = productService.deleteProduct(productId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/getAllProduct")
	public ResponseEntity<PageResponse<ProductDto>>getAllProduct(@RequestParam(name = "PageNumber",defaultValue = "0",required = false)int pageNumber,
			@RequestParam(name = "PageSize",defaultValue = "5",required = false)int pageSize,
			@RequestParam(name = "SortBy", defaultValue = "productTitle", required = false)String sortBy,
			@RequestParam(name = "SortDir",defaultValue = "asc", required = false)String sortDir)
	{
		PageResponse<ProductDto>list = productService.getAllProduct(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/getById/{productId}")
	public ResponseEntity<ProductDto>getByProductId(@PathVariable String productId)
	{
		ProductDto product = productService.getProduct(productId);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProductByCategory/{categoryId}")
	public void deleteProductByCategory(@PathVariable String categoryId)
	{
		productService.deleteProductByCategory(categoryId);
	}
	
	@PutMapping("/updateProductByCategory/{categoryName}/{categoryId}")
	public void updateProductByCategoryName(@PathVariable String categoryName, @PathVariable String categoryId)
	{
		
		productService.updateCategory(categoryName, categoryId);
		
	}
	@GetMapping("/getProductByCategoryName/{categoryName}")
	public List<ProductDto>getProductByCategoryName(@PathVariable String categoryName)
	{
		List<ProductDto>list = productService.getproductByCategoryName(categoryName);
		return list;
	}
	
	
}
