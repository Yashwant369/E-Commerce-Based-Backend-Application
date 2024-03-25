package com.yashwant.user_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto 
{

	private String userId;
	@NotBlank(message = "Please enter user name.")
	@Size(min = 2, max = 256 , message = "User name must be between 2 and 256 characters.")
	private String userName;
	@NotBlank(message = "Please enter user gender.")
	@Size(max = 8, message = "User gender must be between 1 and 8 charcters.")
	private String userGender;
	@NotBlank(message = "Please enter user email.")
	@Email(message = "Please enter valid user email.")
	private String userEmail;
	@NotBlank(message = "Please enter password.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
     message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, "
     		+ "one uppercase letter, one special character, and no whitespace.")
	private String password;
	private String userImage;

}
