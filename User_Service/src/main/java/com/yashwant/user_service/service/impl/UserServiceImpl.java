package com.yashwant.user_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yashwant.user_service.dtos.UserDto;
import com.yashwant.user_service.entity.User;
import com.yashwant.user_service.exception.ResourceNotFoundException;
import com.yashwant.user_service.repository.UserRepo;
import com.yashwant.user_service.service.UserService;
import com.yashwant.user_service.util.ApiResponse;
import com.yashwant.user_service.util.PageResponse;

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
		
		User user = userRepo.findById(userId).orElseThrow(()-> 
		new ResourceNotFoundException("User not found for given user id : " + userId));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()-> 
		new ResourceNotFoundException("User not found for given user id : " + userId));
		user.setUserName(userDto.getUserName());
		user.setUserGender(userDto.getUserGender());
		user.setPassword(userDto.getPassword());
		user.setUserImage(userDto.getUserImage());
		User newUser = userRepo.save(user);
		return mapper.map(newUser, UserDto.class);
	}

	@Override
	public PageResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}
		else 
		{
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<User>page = userRepo.findAll(pageable);
		List<User>list = page.getContent();
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("User not found.");
		}
		List<UserDto>ans = new ArrayList<>();
		for(User u : list)
		{
			UserDto userDto = mapper.map(u, UserDto.class);
			ans.add(userDto);
			
		}
		PageResponse<UserDto>response = new PageResponse<>();
		response.setContent(ans);
		response.setLastpage(page.isLast());
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		return response;
	}

	@Override
	public ApiResponse deleteUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()-> 
		new ResourceNotFoundException("User not found for given user id : " + userId));
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
		
		User user = userRepo.findByUserEmail(email);
		if(user == null)
		{
			throw new ResourceNotFoundException("User not found for given email : " + email);
		}
		return mapper.map(user, UserDto.class);
	}

	@Override
	public PageResponse<UserDto> getbyPrefixName(String name, int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort  = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}
		else 
		{
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<User>page = userRepo.getByName(name,pageable);
		List<User>list = page.getContent();
		if(list.size() == 0)
		{
			throw new ResourceNotFoundException("User not found for given name : " + name);
		}
		List<UserDto>ans = new ArrayList<>();
		for(User u : list)
		{
			UserDto userDto = mapper.map(u, UserDto.class);
			ans.add(userDto);
		}
		PageResponse<UserDto>response = new PageResponse<>();
		response.setContent(ans);
		response.setLastpage(page.isLast());
		response.setPageNumber(page.getNumber());
		response.setPageSize(response.getPageSize());
		response.setTotalElements(response.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		return response;
	}

	
	
	

}
