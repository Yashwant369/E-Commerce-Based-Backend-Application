package com.yashwant.user_service.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yashwant.user_service.util.ApiResponse;

@Configuration
public class MyConfiguration {
	
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
	@Bean
	public ApiResponse getResponse()
	{
		return new ApiResponse();
	}

}
