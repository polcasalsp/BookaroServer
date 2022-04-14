package com.bookaro.server.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookaro.server.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	Optional<User> findByUsername(String username);  
}
