package com.yashwant.cart_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.cart_service.entity.Cart;

public interface CartRepo extends JpaRepository<Cart,String> {

	Cart findByUserId(String userId);
	

}
