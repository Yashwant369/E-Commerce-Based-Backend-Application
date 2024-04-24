package com.yashwant.api_gateway.controllers;

import java.util.Collection;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Clients;

import lombok.Data;

@Data
public class AuthResponse 
{
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String>authorities;
	

}
