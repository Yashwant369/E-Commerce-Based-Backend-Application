package com.yashwant.cart_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.cart_service.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, String> {

	CartItem findByCartItemId(String cartItemId);

}
