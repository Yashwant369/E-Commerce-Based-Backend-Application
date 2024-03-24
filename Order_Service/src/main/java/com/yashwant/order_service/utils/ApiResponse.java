package com.yashwant.order_service.utils;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse {
	
	private String message;
	private boolean success;
	private HttpStatus status;

}
