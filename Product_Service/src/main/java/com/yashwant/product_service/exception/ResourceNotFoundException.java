package com.yashwant.product_service.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException()
	{
		super();
	}
	public ResourceNotFoundException(String mssg)
	{
		super(mssg);
	}

}
