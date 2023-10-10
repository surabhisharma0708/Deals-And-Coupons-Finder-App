package com.dealsandcoupons.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealsandcoupons.login.jwt.JwtService;
import com.dealsandcoupons.login.model.User;
import com.dealsandcoupons.login.service.UserLoginService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/loginService")
public class UserLoginController 
{
	@Autowired 
	UserLoginService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public boolean registerUser(@RequestBody User user)
	{
		return service.registerUser(user);
	}
	@PostMapping("/loginForm")
//	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public boolean loginUser(@RequestBody User user)
	{
		return service.loginUser(user);
	}
	@GetMapping("/getAllUsers")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}
	
	
	@PutMapping("/updateUserById/{id}")
//	@PreAuthorize("hasAuthority('USER')")
	public boolean updateUserByUsername(@RequestBody User user)
	{
		return service.updateUserByUsername(user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean deleteUser(@PathVariable String id)
	{
		return service.deleteUser(id);
	}
	
	@PostMapping("/sendVerificationCode")
	public boolean sendVerificationCode(@RequestBody User user) {
		return service.sendVerificationCode(user);
	}
	
	@GetMapping("/verifyAccount/{token}")
	public boolean verifyAccount(@PathVariable("token") String confirmationToken) {
		return service.verifyAccount(confirmationToken);
	}
	@PostMapping("/generatetoken")

	public User authenticateAndGetToken(@RequestBody User user){ 

		//for token creation

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));

		if(authentication.isAuthenticated()) {

			user.setToken(jwtService.generateToken(user.getEmailId()));

			return user;
		}
		else {

			throw new UsernameNotFoundException("invalid user request!");

		}

	}

	@PostMapping("/validateToken")

	public String validateToken(@RequestParam String token) {

		jwtService.validateByToken(token);

		return "Token is valid";

	}
}
