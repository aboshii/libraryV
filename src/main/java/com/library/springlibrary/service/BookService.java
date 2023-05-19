package com.library.springlibrary.service;

import com.library.springlibrary.model.PublicationComment;
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

    @Transactional
    public void addCommentary(Long id, String username, String opinion){
        Book book = getBookById(id);
        book.getCommentaryList().add(new PublicationComment(username, opinion));
        bookRepository.save(book);
    }

    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }
    public Book getBookByTitle(String title){
        return bookRepository.findByTitleIgnoreCase(title).orElseThrow(BookNotFoundException::new);
    }
    public ArrayList<Book> getBooks(){
        System.out.println("getbookslog");
        return (ArrayList) bookRepository.findAll();
    }
}
