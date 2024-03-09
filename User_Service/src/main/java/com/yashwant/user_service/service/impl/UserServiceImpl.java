package com.yashwant.user_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.entity.User;
import com.yashwant.user_service.repository.UserRepo;
import com.yashwant.user_service.service.UserService;
import com.yashwant.user_service.util.ApiResponse;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto addUser(UserDto userDto) {
		// TODO Auto-generated method stub
		String userId = UUID.randomUUID().toString();
		userDto.setUserId(userId);
		User user = mapper.map(userDto, User.class);
		User newUser = userRepo.save(user);
		return mapper.map(newUser, UserDto.class);
	}

	@Override
	public UserDto getUser(String userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).get();
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).get();
		user.setUserName(userDto.getUserName());
		user.setUserGender(userDto.getUserGender());
		user.setPassword(userDto.getPassword());
		user.setUserImage(userDto.getUserImage());
		User newUser = userRepo.save(user);
		return mapper.map(newUser, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		
		List<User>list = userRepo.findAll();
		List<UserDto>ans = new ArrayList<>();
		for(User u : list)
		{
			UserDto userDto = mapper.map(u, UserDto.class);
			ans.add(userDto);
			
		}
		return ans;
	}

	@Override
	public ApiResponse deleteUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).get();
		userRepo.delete(user);
		ApiResponse response = new ApiResponse();
		response.setMessage("User Deleted for given User Id : " + userId);
		response.setSuccess(true);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public UserDto getByEmail(String email) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByEmail(email);	
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getbyPrefixName(String name) {
		// TODO Auto-generated method stub
		List<User>list = userRepo.getByName(name);
		List<UserDto>ans = new ArrayList<>();
		for(User u : list)
		{
			UserDto userDto = mapper.map(u, UserDto.class);
			ans.add(userDto);
		}
		return ans;
	}

	
	
	

}
