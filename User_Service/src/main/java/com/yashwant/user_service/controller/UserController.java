package com.yashwant.user_service.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.service.impl.FileServiceImpl;
import com.yashwant.user_service.service.impl.UserServiceImpl;
import com.yashwant.user_service.util.ApiResponse;
import com.yashwant.user_service.util.FileResponse;
import com.yashwant.user_service.util.PageResponse;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserServiceImpl userService;
	
	private String path = "Images/Users/";
	
	@Autowired
	private FileServiceImpl fileService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<UserDto>saveUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto user = userService.addUser(userDto);
		return new ResponseEntity<>(user,HttpStatus.OK);
				
	}
	
	@GetMapping("/getUser/{userId}")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId)
	{
		UserDto user = userService.getUser(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
				
	}
	@GetMapping("/getAllUser")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<PageResponse<UserDto>>getAllUser(@RequestParam(name = "PageNumber",defaultValue = "0", required = false)int pageNumber,
			@RequestParam(name = "PageSize",defaultValue = "5",required = false)int pageSize,
			@RequestParam(name = "SortBy", defaultValue = "userName", required = false)String sortBy,
			@RequestParam(name = "SortDir", defaultValue = "asc", required = false)String sortDir)
	{
		PageResponse<UserDto>list = userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getByEmail/{email}")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<UserDto>getUserByEmail(@PathVariable String email)
	{
		UserDto user = userService.getByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/getByName/{name}")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<PageResponse<UserDto>>getUserByName(@PathVariable String name,
			@RequestParam(name = "PageNumber",defaultValue = "0", required = false)int pageNumber,
			@RequestParam(name = "PageSize",defaultValue = "5",required = false)int pageSize,
			@RequestParam(name = "SortBy", defaultValue = "user_name", required = false)String sortBy,
			@RequestParam(name = "SortDir", defaultValue = "asc", required = false)String sortDir)
	{
		PageResponse<UserDto>list = userService.getbyPrefixName(name,pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	//@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable String userId)
	{
		ApiResponse response = userService.deleteUser(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/updateUser/{userId}")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<UserDto>updateUser(@PathVariable String userId,@Valid @RequestBody UserDto userDto)
	{
		UserDto user = userService.updateUser(userId, userDto);
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	@PostMapping("/uploadFile/{userId}")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public ResponseEntity<FileResponse>uploadImage(@RequestParam("userImage")MultipartFile file,
			@PathVariable String userId)
	{
		FileResponse response = fileService.uploadFile(file, path);
		UserDto user = userService.getUser(userId);
		user.setUserImage(response.getImageName());
		UserDto newUser = userService.updateUser(userId, user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/getFile/{userId}")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "userFallBack")
	public void getFile(@PathVariable String userId, HttpServletResponse response)
	{
		UserDto user = userService.getUser(userId);
		InputStream resource = fileService.getFile(path, user.getUserImage());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		try {
			StreamUtils.copy(resource, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResponseEntity<ApiResponse>userFallBack(Exception e)
	{
		ApiResponse response = new ApiResponse();
		response.setSuccess(false);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setMessage("Request limit exceeded");
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	

	

	
}
