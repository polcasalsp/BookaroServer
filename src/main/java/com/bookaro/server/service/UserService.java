package com.bookaro.server.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookaro.server.model.User;
import com.bookaro.server.repo.UserRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@EnableMapRepositories
public class UserService {
	
	@Autowired
	UserRepo repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PreAuthorize(value = "hasRole('ADMIN')")
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> Users = repository.findAll();
        Users.forEach(list::add);
        return list;
    }
    
    @PostAuthorize(value = "hasRole('ADMIN') or principal.equals(returnObject.get().getUsername())")
    public Optional<User> find(Long id) {
        return repository.findById(id);
    }

    //@PreAuthorize(value = "hasRole('ADMIN')")
    public User create(User user) {
        // To ensure the User ID remains unique,
        // use the current timestamp.
    	List<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
    	User copy = new User(
                new Date().getTime(),
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                roles
        );
        return repository.save(copy);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    public User update (User updatedUser) {
    	updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
    	return repository.save(updatedUser);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
