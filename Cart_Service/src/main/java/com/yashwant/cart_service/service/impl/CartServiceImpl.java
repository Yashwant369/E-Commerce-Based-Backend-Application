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
import com.yashwant.cart_service.exception.BadRequestException;
import com.yashwant.cart_service.exception.ResourceNotFoundException;
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
			throw new BadRequestException("Product quantity should be greater than one.");
		}
		String productId = request.getProductId();
		
		ProductDto product = productService.getByProductId(productId);
		if(product == null)
		{
			throw new ResourceNotFoundException("Product not found for given product id :" + productId);
		}
		UserDto user = userService.getUser(userId);
		if(user == null)
		{
			throw new ResourceNotFoundException("User not found for given user id :" + userId);
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
				int quant = i.getProductQuantity();
				i.setProductQuantity(quantity + quant);
				double price = i.getTotalPrice();
				i.setTotalPrice((quantity*product.getProductDiscountPrice())+price);
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
		if(cartItem == null)
		{
			throw new ResourceNotFoundException("Cart item not found for given cart item id : "+cartItemId);
		}
		cartItemRepo.delete(cartItem);
		
	}

	@Override
	public void clearCart(String userId) {
		// TODO Auto-generated method stub
		
		Cart cart = cartRepo.findByUserId(userId);
		if(cart == null)
		{
			throw new ResourceNotFoundException("Cart not found for given user id : " + userId);
		}
		cart.getCartItem().clear();
	    Cart updatedCart = cartRepo.save(cart);
		
	}

	@Override
	public CartDto getCartByUser(String userId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepo.findByUserId(userId);
		if(cart == null)
		{
			throw new ResourceNotFoundException("Cart not found for given user id : " + userId);
		}
		return mapper.map(cart, CartDto.class);
	}

}
