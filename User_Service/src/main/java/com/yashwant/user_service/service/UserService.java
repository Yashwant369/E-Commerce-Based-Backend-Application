package com.yashwant.user_service.service;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.util.ApiResponse;
import com.yashwant.user_service.util.PageResponse;

public interface UserService 
{
	public UserDto addUser(UserDto userDto);
	public UserDto getUser(String userId);
	public UserDto updateUser(String userId, UserDto userDto);
	public PageResponse<UserDto>getAllUser(int pageNumber,int pageSize, String sortBy,String sortDir);
	public ApiResponse deleteUser(String userId);
	public UserDto getByEmail(String email);
	public PageResponse<UserDto>getbyPrefixName(String name, int pageNumber, int pageSize, String sortBy, String sortDir);
	

}
