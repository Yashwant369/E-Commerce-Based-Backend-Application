package com.yashwant.cart_service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yashwant.cart_service.dtos.UserDto;


@FeignClient(name = "USER-SERVICE")
public interface UserService {
	
	@GetMapping("user/getUser/{userId}")
	public UserDto getUser(@PathVariable String userId);
	

}
