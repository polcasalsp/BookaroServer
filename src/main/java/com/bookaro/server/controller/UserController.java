package com.bookaro.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookaro.server.model.User;
import com.bookaro.server.service.UserService;
import com.bookaro.server.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/services/controller/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
    	 List<User> users = service.findAll();
         return ResponseEntity.ok().body(users);
	}

    @GetMapping("/id/{id}")
    public ResponseEntity<User> find(@PathVariable("id") Long id, Principal pri) {
    	Optional<User> user = service.find(id);   
        return ResponseEntity.of(user);
    }

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUsuario (@PathVariable("username") String username) {
		Optional<User> user = service.find(username); 
		return ResponseEntity.of(user);
	}
    
    @PostMapping("/insert")
    public ResponseEntity<User> create(@RequestBody User user) {
    	User created = service.create(user);
    	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
    
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {    	
    	try {
            User user = service.find(id).orElseThrow(() -> new UsernameNotFoundException("User not found."));
            User patchedUser = service.update((User) Utils.applyPatch(patch, user));
            return ResponseEntity.ok(patchedUser);            
        } catch (JsonPatchException | JsonProcessingException e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }    	
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }    
}