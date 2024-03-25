package com.yashwant.user_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yashwant.user_service.entity.User;

public interface UserRepo extends JpaRepository<User,String>
{

	
    
	@Query(value = "select * from user where user_name like :name%", nativeQuery = true)
	Page<User> getByName(String name,Pageable pageable);

	User findByUserEmail(String email);
	

}
