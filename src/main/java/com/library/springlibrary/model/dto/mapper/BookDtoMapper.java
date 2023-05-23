package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import org.springframework.stereotype.Service;

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
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setPublisher(bookDto.getPublisher());
        book.setAuthorFirstName(bookDto.getAuthorFirstName());
        book.setAuthorLastName(bookDto.getAuthorLastName());
        book.setISBN(bookDto.getISBN());
        book.setCommentaryList(bookDto.getCommentaryList());
        return book;
    }
}
