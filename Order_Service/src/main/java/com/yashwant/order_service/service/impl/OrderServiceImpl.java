package com.yashwant.order_service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yashwant.order_service.dtos.CartDto;
import com.yashwant.order_service.dtos.CartItemDto;
import com.yashwant.order_service.dtos.OrderDto;
import com.yashwant.order_service.dtos.UserDto;
import com.yashwant.order_service.entity.Orders;
import com.yashwant.order_service.entity.OrderItem;
import com.yashwant.order_service.externals.CartService;
import com.yashwant.order_service.externals.UserService;
import com.yashwant.order_service.repo.OrderRepo;
import com.yashwant.order_service.service.OrderService;
import com.yashwant.order_service.utils.ApiResponse;
import com.yashwant.order_service.utils.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	

	@Override
	public OrderDto createOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		String userId = request.getUserId();
		UserDto user =  userService.getUser("dd15eee3-1a3f-474e-92d7-d3cabcea104d");
		if(user == null)
		{
			//
		}
		CartDto cart = cartService.getCart("dd15eee3-1a3f-474e-92d7-d3cabcea104d");
		if(cart == null)
		{
			//throw new ResourceNotFoundException("Cart not found for given user");
		}
		List<CartItemDto>items = cart.getCartItem();
		if(items.size() == 0)
		{
			//throw new BadRequestException("No cart items found for user");
		}
		Orders order = new Orders();
		String orderId = UUID.randomUUID().toString();
		order.setOrderId(orderId);
		order.setOrderDate(new Date());
		order.setBillingName(request.getBillingName());
		order.setBillingAddress(request.getOrderAddress());
		order.setOrderStatus(request.getOrderStatus());
		order.setPaymentStatus(request.getPaymentStatus());
		order.setPhoneNumber(request.getPhoneNumber());
		order.setUserId(request.getUserId());
		double amount = 0;
		List<OrderItem>list = order.getOrderItems();
		for(CartItemDto item : items)
		{
			OrderItem orderItem = new OrderItem();
			String orderItemId = UUID.randomUUID().toString();
			orderItem.setOrderItemId(orderItemId);
			orderItem.setQuantity(item.getProductQuantity());
			orderItem.setOrder(order);
			orderItem.setProductId(item.getProductId());
			orderItem.setTotalPrice(item.getTotalPrice());
			amount += item.getTotalPrice();
			list.add(orderItem);
		}
		order.setPaymentAmount(amount);
		ApiResponse response = cartService.clearCart(request.getUserId());
		Orders newOrder = orderRepo.save(order);
		return mapper.map(newOrder, OrderDto.class);
		
	}

	@Override
	public void removeOrder(String orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepo.findById(orderId).get();
		orderRepo.delete(order);
		
	}

	@Override
	public List<OrderDto> getUserOrder(String userId) {
		// TODO Auto-generated method stub
		UserDto user = userService.getUser(userId);
		if(user == null)
		{
			
		}
		List<Orders>orders = orderRepo.findByUserId(userId);
		List<OrderDto>list = new ArrayList<>();
		for(Orders o : orders)
		{
			OrderDto oDto = mapper.map(o, OrderDto.class);
			list.add(oDto);
		}
		return list;
		
	}

	@Override
	public List<OrderDto> getAllOrder() {
		// TODO Auto-generated method stub
		List<Orders>orders = orderRepo.findAll();
		List<OrderDto>list = new ArrayList<>();
		for(Orders o : orders)
		{
			OrderDto oDto = mapper.map(o, OrderDto.class);
			list.add(oDto);
		}
		return list;
		
	}

	@Override
	public OrderDto updateOrder(String orderId) {
		// TODO Auto-generated method stub

		Orders order = orderRepo.findById(orderId).get();
		order.setOrderStatus("Delivered");
		order.setPaymentStatus("Done");
		return mapper.map(order, OrderDto.class);
		
	}

}
