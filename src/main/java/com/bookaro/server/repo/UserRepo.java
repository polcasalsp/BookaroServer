package com.bookaro.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookaro.server.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{

	User findByUsername(String username); 
 
}
