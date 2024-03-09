package com.yashwant.user_service.dtos;

import lombok.Data;

@Data
public class UserDto 
{

	private String userId;
	private String userName;
	private String userGender;
	private String userEmail;
	private String password;
	private String userImage;

}
