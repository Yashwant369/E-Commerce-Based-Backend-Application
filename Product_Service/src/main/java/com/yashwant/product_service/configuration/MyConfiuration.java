package com.yashwant.product_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiuration 
{
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}

}
