package com.Book_Collection.Book_Collection.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Book_Collection.Book_Collection.exception.InvalidUsernameException;
import com.Book_Collection.Book_Collection.model.User;
import com.Book_Collection.Book_Collection.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	public User signUp(User user) throws InvalidUsernameException {
		//check for username duplicacy 
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
		
		//encrypt the password 
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		
		
		return userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		 //i am sure that username is valid as Spring has already checked it 
		return userRepository.findByUsername(username).get();
	}

}