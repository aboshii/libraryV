package com.library.springlibrary.service;

import com.library.springlibrary.model.PublicationComment;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import lombok.AllArgsConstructor;
import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {
    private BookRepository bookRepository;
    private BookDtoMapper bookDtoMapper;

    @Transactional
    public BookDto addBook(BookDto bookDto) {
        Book book = bookDtoMapper.map(bookDto);
        bookRepository.save(book);
        return bookDtoMapper.map(book);
    }

    @Transactional
    public void addComment(Long id, String username, String opinion) {
        Book book = getBookById(id);
        book.getCommentaryList().add(new PublicationComment(username, opinion));
        bookRepository.save(book);
    }

    @Transactional
    public void borrowBook(Optional<Book> optionalBook, Optional<User> optionalUser) {
        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book book = optionalBook.get();
            book.setBorrower(optionalUser.get());
            bookRepository.save(book);
        }
    }

    public Book getBookById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }
    public BookDto getBookDtoById(Long id) throws BookNotFoundException {
        return bookDtoMapper.map(bookRepository.findById(id).orElseThrow(BookNotFoundException::new));
    }

    public BookDto getBookByTitle(String title) {
        return bookDtoMapper.map(
                bookRepository
                        .findByTitleIgnoreCase(title)
                        .orElseThrow(BookNotFoundException::new));
    }

    public ArrayList<Book> getBooks() {
        return (ArrayList) bookRepository.findAll();
    }

    public Optional<BookDto> replaceBook(Long id, BookDto bookDto){
        if (!bookRepository.existsById(id)){
            return Optional.empty();
        }
        bookDto.setId(id);
        Book book = bookDtoMapper.map(bookDto);
        Book savedBook = bookRepository.save(book);
        return Optional.of(bookDtoMapper.map(savedBook));
    }
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public void updateBook (BookDto bookDto) {
        Book book = bookDtoMapper.map(bookDto);
        bookRepository.save(book);
    }
}
