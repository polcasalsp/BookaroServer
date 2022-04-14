package com.bookaro.server.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bookaro.server.model.Book;
import com.bookaro.server.model.User;
import com.bookaro.server.repo.BookRepository;
import com.bookaro.server.repo.UserRepo;

@Component
public class DbInit {
 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private BookRepository bookRepository;
 
    @PostConstruct
    private void postConstruct() {
        addUsers();
        addBooks();
    }
    
    private void addUsers() {
    	User admin = new User(1L, "admin", passwordEncoder.encode("admin"), "admin@bookaro.es", "Pol", "Casals", "ADMIN");
        User normalUser = new User(2L, "user", passwordEncoder.encode("user"), "user@bookaro.es", "Random", "User", "USER");
        userRepository.save(admin);
        userRepository.save(normalUser);
    }
    
    private void addBooks() {
    	Book book1 = new Book(1L, "Game of Thrones1", "George R. R. Martin", "isbn", "Fantasy", "Gigamesh", "synopsis");
    	Book book2 = new Book(2L, "Game of Thrones2", "George R. R. Martin", "isbn", "Fantasy", "Gigamesh", "synopsis");
    	Book book3 = new Book(3L, "Game of Thrones3", "George R. R. Martin", "isbn", "Fantasy", "Gigamesh", "synopsis");
    	Book book4 = new Book(4L, "Game of Thrones4", "George R. R. Martin", "isbn", "Fantasy", "Gigamesh", "synopsis");
    	Book book5 = new Book(5L, "Game of Thrones5", "George R. R. Martin", "isbn", "Fantasy", "Gigamesh", "synopsis");
    	Book book6 = new Book(6L, "Game of Thrones6", "George R. R. Martin", "isbn", "Fantasy", "Gigamesh", "synopsis");
    	bookRepository.save(book1);
    	bookRepository.save(book2);
    	bookRepository.save(book3);
    	bookRepository.save(book4);
    	bookRepository.save(book5);
    	bookRepository.save(book6);
    }
}