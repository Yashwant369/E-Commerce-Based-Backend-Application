package com.yashwant.user_service.util;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class FileResponse {
	
	private String imageName;
	private String message;
	private boolean success;
	private HttpStatus status;
	
}
