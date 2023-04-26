package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.mapper.BookDto;
import com.library.springlibrary.mapper.BookMapper;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.Book2Dto;
import com.library.springlibrary.repository.Book2Repository;
import com.library.springlibrary.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final Book2Repository book2Repository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, Book2Repository book2Repository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.book2Repository = book2Repository;
        this.bookMapper = bookMapper;
    }

    @Transactional
    public void addBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getTitle(),
                bookDto.getPublicationYear(),
                bookDto.getPublisher(),
                bookDto.getAuthorFirstName(),
                bookDto.getAuthorLastName(),
                bookDto.getISBN());
        bookRepository.save(book);
    }

    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public void dodajBook(Book2Dto dto) {
        book2Repository.save(bookMapper.map(dto));
    }
}
