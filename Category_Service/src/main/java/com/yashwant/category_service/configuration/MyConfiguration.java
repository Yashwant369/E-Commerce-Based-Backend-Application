package com.yashwant.category_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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

}
