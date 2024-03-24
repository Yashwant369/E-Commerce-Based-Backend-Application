package com.yashwant.order_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.order_service.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, String>{

}
