package com.yashwant.category_service.exception;

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
