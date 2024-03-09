package com.yashwant.user_service.service;

import com.yashwant.user_service.entity.User;

public interface UserService 
{
	public User addUser(User user);
	public User getUser(String userId);

}
