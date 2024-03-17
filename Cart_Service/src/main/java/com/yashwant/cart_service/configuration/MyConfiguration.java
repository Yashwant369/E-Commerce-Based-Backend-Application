package com.yashwant.cart_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
@Configuration
public class MyConfiguration {
	
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}

}
