package com.library.springlibrary.repository;

import com.library.springlibrary.model.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@ComponentScan("com.library.springlibrary.model")
public interface BookRepository extends CrudRepository<Book, Long> {

}