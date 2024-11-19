package com.Book_Collection.Book_Collection.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Book_Collection.Book_Collection.model.User;
import com.Book_Collection.Book_Collection.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional= userRepository.findByUsername(username);
		if(optional.isEmpty())
			throw new UsernameNotFoundException("Invalid Username");
		User user = optional.get();
		return user;
	}
}