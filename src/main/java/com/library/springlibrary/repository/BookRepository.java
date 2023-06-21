package com.library.springlibrary.repository;

import com.library.springlibrary.model.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@ComponentScan("com.library.springlibrary.model")
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE title = ?1 LIMIT 1", nativeQuery = true)
    Optional<Book> findByTitleIgnoreCase(String title);

}