package com.yashwant.order_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.order_service.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders,String>{

	List<Orders> findByUserId(String userId);

}
