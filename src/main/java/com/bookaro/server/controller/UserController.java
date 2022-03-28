package com.bookaro.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookaro.server.model.User;
import com.bookaro.server.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services/controller/user")
public class UserController {
	
	@Autowired
	private UserService service;

    public UserController(UserService service) {
    	this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
    	 List<User> users = service.findAll();
         return ResponseEntity.ok().body(users);
	}

    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable("id") Long id) {
    	Optional<User> user = service.find(id);
        return ResponseEntity.of(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
    	User created = service.create(user);
    	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable("id") Long id,
            @RequestBody User updatedUser) {

        Optional<User> updated = service.update(id, updatedUser);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    User created = service.create(updatedUser);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}