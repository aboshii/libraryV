package com.library.springlibrary.controller;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.PublicationCommentDto;
import com.library.springlibrary.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
class BookEndpoint {
    @Autowired
    private final BookService bookService;
    @GetMapping()
    String emptyMapping() {
        return "Empty!";
    }
    @GetMapping("/books")
    List<Book> getBookList(){
        return bookService.getBooks();
    }

    @RequestMapping("/books/{id}")
    Book getBook(@PathVariable(required = false, name = "id") Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books/addbookpost")
    String addNewBook(@RequestParam String title,
                    @RequestParam int publicationYear,
                    @RequestParam String publisher,
                    @RequestParam String authorFirstName,
                    @RequestParam String authorLastName,
                    @RequestParam String ISBN){
        bookService.addBook(new BookDto(title,
                Year.of(publicationYear), publisher, authorFirstName, authorLastName, ISBN));
        return "redirect:books";
    }
    @PostMapping("/setcommentpost")
    String addNewCommentary(@RequestHeader(name = "Referer") String referer,
                            PublicationCommentDto publicationCommentDto) {
        long id = Long.parseLong(UrlHandler.getParameterFromReferer(referer, "id"));
        bookService.addCommentary(id, publicationCommentDto.getUsername(), publicationCommentDto.getDescription());
        return UriComponentsBuilder
                .fromPath("redirect:book/" + id)
                .build()
                .toString();
        //response.sendRedirect("/book?id=" + id);
        //return book(Long.toString(id), model);
    }
}