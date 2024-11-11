package com.springboot.banking_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.model.User;
import com.springboot.banking_system.repository.UserRepository;

@Service
public class UserSecurityService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUsername(username);
		if(optional.isEmpty())
			throw new UsernameNotFoundException("Invalid username"); 
		
		User user = optional.get();
		return user;
	}
}