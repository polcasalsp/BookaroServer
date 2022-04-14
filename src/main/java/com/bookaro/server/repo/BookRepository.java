package com.bookaro.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bookaro.server.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
