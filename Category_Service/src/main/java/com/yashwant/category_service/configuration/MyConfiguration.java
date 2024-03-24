package com.yashwant.category_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.yashwant.category_service.util.ApiResponse;

@Configuration
public class MyConfiguration 
{
	
	@Bean
	public RestTemplate getTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean 
	public ModelMapper getmapper()
	{
		return new ModelMapper();
	}
	@Bean
	public ApiResponse getResponse()
	{
		return new ApiResponse();
	}

}
