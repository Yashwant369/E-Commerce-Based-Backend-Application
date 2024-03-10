package com.yashwant.category_service.util;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse 
{
	private String message;
	private boolean success;
	private HttpStatus status;

}
