package com.yashwant.cart_service.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yashwant.cart_service.dtos.CartDto;
import com.yashwant.cart_service.dtos.ProductDto;
import com.yashwant.cart_service.dtos.UserDto;
import com.yashwant.cart_service.entity.Cart;
import com.yashwant.cart_service.entity.CartItem;
import com.yashwant.cart_service.external.ProductService;
import com.yashwant.cart_service.external.UserService;
import com.yashwant.cart_service.repo.CartItemRepo;
import com.yashwant.cart_service.repo.CartRepo;
import com.yashwant.cart_service.service.CartService;
import com.yashwant.cart_service.util.AddItemRequest;

@Service
public class CartServiceImpl implements CartService{
	
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CartItemRepo cartItemRepo;
	


	@Override
	public CartDto addItem(String userId, AddItemRequest request) {
		// TODO Auto-generated method stub
		
		int quantity = request.getQuantity();
		if(quantity < 1)
		{
			// throw exception
		}
		String productId = request.getProductId();
		
		ProductDto product = productService.getByProductId(productId);
		if(product == null)
		{
			// throw exception
		}
		UserDto user = userService.getUser(userId);
		if(user == null)
		{
			//throw exception 
		}
		Cart cart = null;
		cart = cartRepo.findByUserId(userId);
		if(cart == null)
		{
			cart = new Cart();
			cart.setCreatedDate(new Date());
			cart.setUserId(userId);
			cart.setCartId(UUID.randomUUID().toString());
		}
		List<CartItem>items = cart.getCartItem();
		
		boolean check = false;
		for(CartItem i : items)
		{
			if(i.getProductId().equals(productId))
			{
				i.setProductQuantity(quantity);
				i.setTotalPrice(quantity*product.getProductDiscountPrice());
				check = true;
			}
		}
		if(check == false)
		{
			CartItem cartItem = new CartItem();
			cartItem.setCartItemId(UUID.randomUUID().toString());
			cartItem.setProductQuantity(quantity);
			cartItem.setTotalPrice(quantity*product.getProductDiscountPrice());
			cartItem.setProductId(productId);
			cartItem.setCart(cart);
			items.add(cartItem);
			
		}
		Cart newCart = cartRepo.save(cart);
		return mapper.map(newCart, CartDto.class);
	}

	@Override
	public void removeItem(String cartItemId) {
		// TODO Auto-generated method stub
		CartItem cartItem = cartItemRepo.findByCartItemId(cartItemId);
		cartItemRepo.delete(cartItem);
		
	}

	@Override
	public void clearCart(String userId) {
		// TODO Auto-generated method stub
		
		Cart cart = cartRepo.findByUserId(userId);
		cart.getCartItem().clear();
		cartRepo.save(cart);
		
	}

	@Override
	public CartDto getCartByUser(String userId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepo.findByUserId(userId);
		return mapper.map(cart, CartDto.class);
	}

}
