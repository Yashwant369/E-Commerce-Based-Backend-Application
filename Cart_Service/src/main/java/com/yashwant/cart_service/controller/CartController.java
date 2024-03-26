package com.yashwant.cart_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.cart_service.dtos.CartDto;
import com.yashwant.cart_service.service.impl.CartServiceImpl;
import com.yashwant.cart_service.util.AddItemRequest;
import com.yashwant.cart_service.util.ApiResponse;

@RestController
@RequestMapping("/cart")
public class CartController 
{
	@Autowired
	private CartServiceImpl cartService;
	
	@PostMapping("/addCart/{userId}")
	public ResponseEntity<CartDto>saveCart(@Valid @RequestBody AddItemRequest request, @PathVariable String userId)
	{
		CartDto cartDto = cartService.addItem(userId, request);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}
	
	@GetMapping("/clearCart/{userId}")
	public ResponseEntity<ApiResponse>clearCart(@PathVariable String userId)
	{
		cartService.clearCart(userId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Cart cleared for given user id : " + userId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/removeItem/{cartItemId}")
	public ResponseEntity<ApiResponse>removeItem(@PathVariable String cartItemId)
	{
		cartService.removeItem(cartItemId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Cart item removed for given cart item id : " + cartItemId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
		
	}
	@GetMapping("/getCart/{userId}")
	public ResponseEntity<CartDto>getCart(@PathVariable String userId)
	{
		CartDto cart = cartService.getCartByUser(userId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

}
