package com.bookaro.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookaro.server.model.User;
import com.bookaro.server.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
    	
    	Optional<User> user = userRepo.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User " + username +" not found");
		}  
		
		return new User(user.get().getUsername(), user.get().getPassword(), user.get().getRole());
    }
}