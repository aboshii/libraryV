package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public void borrowBook(Optional<Book> optionalBook, Optional<User> optionalUser){
        if(optionalBook.isPresent() && optionalUser.isPresent()){
            Book book = optionalBook.get();
            book.setBorrower(optionalUser.get());
            bookRepository.save(book);
        }
    }

    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }
}
