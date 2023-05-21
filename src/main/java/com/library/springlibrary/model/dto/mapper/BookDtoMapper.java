package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookDtoMapper {
    public BookDto map(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setAuthorFirstName(book.getAuthorFirstName());
        bookDto.setAuthorLastName(book.getAuthorLastName());
        bookDto.setISBN(book.getISBN());
        bookDto.setBorrowerData(book.getBorrower().getUserData());
        bookDto.setCommentaryList(book.getCommentaryList());
        return bookDto;
    }
}
