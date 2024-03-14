package com.yashwant.user_service.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
	
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}

}
