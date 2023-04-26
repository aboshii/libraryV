package com.library.springlibrary.mapper;

import com.library.springlibrary.model.Book2;
import com.library.springlibrary.model.dto.Book2Dto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book2 map(Book2Dto bookDto) {
        Book2 book2 = new Book2(bookDto.getTitle(), bookDto.getPublisher(), bookDto.getISBN());
        book2.setISBN(bookDto.getISBN());
        book2.setTitle(book2.getTitle());
        book2.setPublisher(book2.getPublisher());
        return book2;
    }
}
