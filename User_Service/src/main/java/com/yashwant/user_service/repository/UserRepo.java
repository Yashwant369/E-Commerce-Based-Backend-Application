package com.yashwant.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.user_service.entity.User;

public interface UserRepo extends JpaRepository<User,String>
{

}
