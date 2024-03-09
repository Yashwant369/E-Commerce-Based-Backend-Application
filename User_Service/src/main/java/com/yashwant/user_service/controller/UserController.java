package com.yashwant.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.user_service.entity.User;
import com.yashwant.user_service.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user)
	{
		User newUser = userService.addUser(user);
		return newUser;
	}
	
	@GetMapping("/getUser/{userId}")
	public User getUser(@PathVariable String userId)
	{
		User user = userService.getUser(userId);
		return user;
	}
	

}
