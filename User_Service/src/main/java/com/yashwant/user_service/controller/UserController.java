package com.yashwant.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.service.impl.UserServiceImpl;
import com.yashwant.user_service.util.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<UserDto>saveUser(@RequestBody UserDto userDto)
	{
		UserDto user = userService.addUser(userDto);
		return new ResponseEntity<>(user,HttpStatus.OK);
				
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId)
	{
		UserDto user = userService.getUser(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
				
	}
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>>getAllUser()
	{
		List<UserDto>list = userService.getAllUser();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<UserDto>getUserByEmail(@PathVariable String email)
	{
		UserDto user = userService.getByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<UserDto>>getUserByName(@PathVariable String name)
	{
		List<UserDto>list = userService.getbyPrefixName(name);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable String userId)
	{
		ApiResponse response = userService.deleteUser(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto>updateUser(@PathVariable String userId,@RequestBody UserDto userDto)
	{
		UserDto user = userService.updateUser(userId, userDto);
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	

	
}
