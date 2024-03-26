package com.yashwant.user_service.exception;

public class BadRequestException extends RuntimeException {
	
	public BadRequestException()
	{
		super();
	}
	public BadRequestException(String mssg)
	{
		super(mssg);
	}

}
