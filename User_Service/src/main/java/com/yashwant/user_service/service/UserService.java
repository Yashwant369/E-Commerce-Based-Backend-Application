package com.yashwant.user_service.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.util.ApiResponse;
import com.yashwant.user_service.util.FileResponse;
import com.yashwant.user_service.util.PageResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface UserService 
{
	public UserDto addUser(UserDto userDto);
	public UserDto getUser(String userId);
	public UserDto updateUser(String userId, UserDto userDto);
	public PageResponse<UserDto>getAllUser(int pageNumber,int pageSize, String sortBy,String sortDir);
	public ApiResponse deleteUser(String userId);
	public UserDto getByEmail(String email);
	public PageResponse<UserDto>getbyPrefixName(String name, int pageNumber, int pageSize, String sortBy, String sortDir);
	public FileResponse uploadFile(MultipartFile file, String userId);
	public void getFile(String userId, HttpServletResponse response);

}
