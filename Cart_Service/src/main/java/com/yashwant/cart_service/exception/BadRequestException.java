package com.yashwant.cart_service.exception;


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
