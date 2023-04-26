package com.library.springlibrary.controller;

import com.library.springlibrary.mapper.BookMapper;
import com.library.springlibrary.model.dto.Book2Dto;
import com.library.springlibrary.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping(value = "/add")
    public void addClient(@RequestBody Book2Dto book) {
        log.info("Pa czy dziala");
        log.info(book.toString());
        log.info(bookMapper.map(book).toString());
        bookService.dodajBook(book);
    }
}
