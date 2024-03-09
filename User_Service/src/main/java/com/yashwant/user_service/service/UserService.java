package com.yashwant.user_service.service;

import java.util.List;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.util.ApiResponse;

public interface UserService 
{
	public UserDto addUser(UserDto userDto);
	public UserDto getUser(String userId);
	public UserDto updateUser(String userId, UserDto userDto);
	public List<UserDto> getAllUser();
	public ApiResponse deleteUser(String userId);
	public UserDto getByEmail(String email);
	public List<UserDto>getbyPrefixName(String name);
	
	

}
