package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class BookDtoMapper {
    public BookDto map(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setAuthorFirstName(book.getAuthorFirstName());
        bookDto.setAuthorLastName(book.getAuthorLastName());
        bookDto.setISBN(book.getISBN());
        bookDto.setCommentaryList(book.getCommentaryList());
        if (book.getBorrower() != null) {
            bookDto.setBorrowerData(book.getBorrower().getUserData());
        }
        return bookDto;
    }

    public Book map(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        if (bookDto.getPublicationYear().getValue() <= Year.now().getValue()){
            book.setPublicationYear(bookDto.getPublicationYear());
        } else
        {
            book.setPublicationYear(Year.of(Year.now().getValue()));
        }
        book.setPublisher(bookDto.getPublisher());
        book.setAuthorFirstName(bookDto.getAuthorFirstName());
        book.setAuthorLastName(bookDto.getAuthorLastName());
        book.setISBN(bookDto.getISBN());
        book.setCommentaryList(bookDto.getCommentaryList());
        return book;
    }
}
