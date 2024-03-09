package com.yashwant.user_service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class User 
{
	@Id
	private String userId;
	private String userName;
	private String userGender;
	@Column(unique = true)
	private String userEmail;
	private String password;
	private String userImage;
	@Transient
	private Cart cart;	
	@Transient
	private List<Orders>orders = new ArrayList<>();

}
