package com.yashwant.user_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yashwant.user_service.entity.User;
import com.yashwant.user_service.repository.UserRepo;
import com.yashwant.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepo userRepo;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		User newUser = userRepo.save(user);
		return newUser;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).get();
		return user;
	}
	
	

}