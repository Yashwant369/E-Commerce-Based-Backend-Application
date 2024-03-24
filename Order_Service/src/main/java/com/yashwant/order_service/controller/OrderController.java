package com.yashwant.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.order_service.dtos.CartDto;
import com.yashwant.order_service.dtos.OrderDto;
import com.yashwant.order_service.dtos.UserDto;
import com.yashwant.order_service.externals.CartService;
import com.yashwant.order_service.externals.UserService;
import com.yashwant.order_service.service.impl.OrderServiceImpl;
import com.yashwant.order_service.utils.ApiResponse;
import com.yashwant.order_service.utils.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/createOrder")
	public ResponseEntity<OrderDto>createOrder(@RequestBody OrderRequest orderRequest)
	{
		OrderDto order = orderService.createOrder(orderRequest);
		return new ResponseEntity<>(order,HttpStatus.OK);
		
	}
	
	@GetMapping("/deleteOrder/{orderId}")
	public ResponseEntity<ApiResponse>deleteOrder(@PathVariable String orderId)
	{
		orderService.removeOrder(orderId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Order deleted for given Order id : " + orderId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("getAllOrder")
	public ResponseEntity<List<OrderDto>>getAllOrder()
	{
		List<OrderDto>list = orderService.getAllOrder();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("getUserOrder/{userId}")
	public ResponseEntity<List<OrderDto>>getUserOrder(@PathVariable String userId)
	{
		List<OrderDto>list = orderService.getUserOrder(userId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/updateOrder/{orderId}")
	public ResponseEntity<OrderDto>updateOrder(@PathVariable String orderId)
	{
		OrderDto order = orderService.updateOrder(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public CartDto getCart(@PathVariable String userId)
	{
		return cartService.getCart(userId);
	}
	@GetMapping("/user/{userId}")
	public UserDto getuser(@PathVariable String  userId)
	{
		return userService.getUser(userId);
	}

}
