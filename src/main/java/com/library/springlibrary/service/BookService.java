package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.PublicationComment;
import com.library.springlibrary.model.dto.PublicationCommentDto;
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
import java.util.List;
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
    public void addComment(Long id, PublicationCommentDto publicationCommentDto) {
        Book book = getBookById(id);
        book.getCommentaryList().add
                (new PublicationComment(publicationCommentDto.getUsername(), publicationCommentDto.getDescription()));
        bookRepository.save(book);
    }

    @Transactional
    public void borrowBook(Optional<Book> optionalBook, Optional<User> optionalUser) {
        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book book = getBookById(optionalBook.get().getId());
            book.setBorrower(optionalUser.get());
            bookRepository.save(book);
        } else if (optionalBook.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        } else if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
    }

    public Book getBookById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }
    public BookDto getBookDtoById(Long id) throws BookNotFoundException {
        return bookDtoMapper.map(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found")));
    }

    public BookDto getBookByTitle(String title) {
        return bookDtoMapper.map(
                bookRepository
                        .findByTitleIgnoreCase(title)
                        .stream().findAny()
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
    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public void updateBook (BookDto bookDto) {
        Book newBookData = bookDtoMapper.map(bookDto);
        bookRepository
                .findById(newBookData.getId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.save(newBookData);
    }
}
