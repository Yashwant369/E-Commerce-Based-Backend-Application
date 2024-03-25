package com.yashwant.user_service.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yashwant.user_service.util.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private ApiResponse response;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>handlerResourceNotFound(ResourceNotFoundException ex)
	{
		response.setMessage(ex.getMessage());
		response.setSuccess(false);
		response.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>handlerMethodArgumentNotValid(MethodArgumentNotValidException ex)
	{
		Map<String,String>map = new HashMap<>();
		List<ObjectError>list = ex.getBindingResult().getAllErrors();
		for(ObjectError error : list)
		{
			String message = error.getDefaultMessage();
			String field = ((FieldError)error).getField();
			map.put(field, message);
		}
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}

}
