package com.bookaro.server.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.map.repository.config.EnableMapRepositories;
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

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> Users = repository.findAll();
        Users.forEach(list::add);
        return list;
    }

    public Optional<User> find(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        // To ensure the User ID remains unique,
        // use the current timestamp.
    	User copy = new User(
                new Date().getTime(),
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
        );
        return repository.save(copy);
    }

    public Optional<User> update( Long id, User newUser) {
        // Only update an User if it can be found first.
        return repository.findById(id)
                .map(oldUser -> {
                	User updated = oldUser.updateWith(newUser);
                   return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
