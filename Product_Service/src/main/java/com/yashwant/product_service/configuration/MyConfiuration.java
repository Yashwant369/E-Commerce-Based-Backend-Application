package com.yashwant.product_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yashwant.product_service.util.ApiResponse;

@Configuration
public class MyConfiuration 
{
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
