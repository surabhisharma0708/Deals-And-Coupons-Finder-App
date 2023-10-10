package com.dealsandcoupons.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User 
{
	@Id
	private String emailId;
	private String password;
	private String roles;
	private String token;
	
}
