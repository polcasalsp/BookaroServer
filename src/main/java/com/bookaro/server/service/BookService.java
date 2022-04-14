package com.bookaro.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookaro.server.model.Book;
import com.bookaro.server.repo.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public Iterable<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Optional<Book> findById (Long id) {
		return bookRepository.findById(id);
	}
	
	public Book add (Book book) {
		return bookRepository.save(book);
	}
	
	public boolean update(Book book) {
	    try {
	    	bookRepository.save(book);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
	
	public boolean delete (long id) {
		try {
			bookRepository.deleteById(id);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

}
