package com.yashwant.api_gateway.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.group.Group;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.authc.credentials.TokenClientCredentials;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model)
	{
		AuthResponse response = new AuthResponse();
		response.setUserId(user.getEmail());
		response.setAccessToken(client.getAccessToken().getTokenValue());
		response.setRefreshToken(client.getRefreshToken().getTokenValue());
		response.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		//<>list = user.getAuthorities();
		
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	 private final String OKTA_ORG_URL = "https://dev-18855751.okta.com/oauth2/default";
	    private final String OKTA_API_TOKEN = "00FcE2MHkgwBQXQ0XRTy74VmMOG-ZnJpvcwFU_Gx9e";
	    private final String NORMAL_USER_GROUP_NAME = "Normal User";
	    
	@GetMapping("/register")
	public String reg()
	{
		  Client client = Clients.builder()
                  .setOrgUrl(OKTA_ORG_URL)
                  .setClientCredentials(new TokenClientCredentials(OKTA_API_TOKEN))
                  .build();

// Create the user in Okta
User user = UserBuilder.instance()
                  .setFirstName("firstName")
                  .setLastName("lastName")
                  .setEmail("email@gmail.com")
                  .setPassword("Password@134".toCharArray())
                  .buildAndCreate(client);

// Find the normal user group by name
Group group = client.listGroups(NORMAL_USER_GROUP_NAME, null, null).single();

// Add the user to the normal user group
user.addToGroup(group.getId());
		
		return "yes";
	}

}
