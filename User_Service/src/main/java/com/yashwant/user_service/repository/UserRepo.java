package com.yashwant.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yashwant.user_service.entity.User;

public interface UserRepo extends JpaRepository<User,String>
{

	User findByEmail(String email);
    
	@Query(value = "select * from user where user_name like :name%", nativeQuery = true)
	List<User> getByName(String name);
	

}
