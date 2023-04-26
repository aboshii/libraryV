package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.mapper.BookDto;
import com.library.springlibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

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
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }
}
