package com.library.springlibrary.controller.Endpoints;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.service.BookService;
import com.library.springlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.SecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/admin",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class AdminEndpoint {
    private final BookService bookService;
    private final UserService userService;

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void borrowBook(@RequestParam Long bookId,
                    @RequestParam Long userId) {
        Optional optionalBook = Optional.of(bookService.getBookById(bookId));
        Optional optionalUser = Optional.of(userService.getUserById(userId));
        bookService.borrowBook(optionalBook, optionalUser);
    }
}