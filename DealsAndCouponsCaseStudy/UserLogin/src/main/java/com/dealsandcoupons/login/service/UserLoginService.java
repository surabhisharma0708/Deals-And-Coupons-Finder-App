package com.dealsandcoupons.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dealsandcoupons.login.Repository.TokenRepo;
import com.dealsandcoupons.login.Repository.UserLoginRepository;
import com.dealsandcoupons.login.model.ConfirmationToken;
import com.dealsandcoupons.login.model.User;

@Service
public class UserLoginService 
{
	@Autowired
	private UserLoginRepository userRepository;
	@Autowired
	private TokenRepo tokenRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public boolean registerUser(User user)
	{
		Optional<User> optionalUser = userRepository.findById(user.getEmailId());
		if(!optionalUser.isEmpty())
		{
			return false;
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return true;
	}
	public boolean loginUser(User user)
	{
		Optional<User> obj = userRepository.findById(user.getEmailId());
		if(obj.isEmpty()) {
			return false;
		}
		return true;
	}
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public boolean updateUserByUsername(User user)
	{
		Optional<User>  userOptional =userRepository.findById(user.getEmailId());
		if (userOptional.isEmpty()) 
		{
	        return false;
	    } 
		userRepository.save(user);
		return true;
	}
	public boolean deleteUser(String emailId)
	{
		 userRepository.deleteById(emailId);
		 return true;
	}
	
	public boolean sendVerificationCode(User user) {
		Optional<User> obj = userRepository.findById(user.getEmailId());
		if(!obj.isEmpty()) {
			return false;
		}
		else {
//			authRepo.save(authDetail);
			ConfirmationToken confirmationToken  = new ConfirmationToken(user);
			tokenRepo.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmailId());
			mailMessage.setSubject("Complete Registration");
			mailMessage.setText("To confirm your account, enter this code : " +
								confirmationToken.getConfirmationToken());
			emailService.sendMail(mailMessage);
			return true;
		}
	}
	public boolean verifyAccount(String confirmationToken) {
		Optional<ConfirmationToken> obj = tokenRepo.findById(confirmationToken);
		if(!obj.isEmpty()) {
			ConfirmationToken token = obj.get();
			userRepository.save(token.getUser());
//			tokenRepo.deleteById(confirmationToken);
			return true;
		}
		return false;
	}
}
