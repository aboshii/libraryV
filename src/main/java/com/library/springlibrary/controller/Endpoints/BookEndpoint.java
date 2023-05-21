package com.library.springlibrary.controller.Endpoints;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.PublicationCommentDto;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/books",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
class BookEndpoint {
    private BookService bookService;
    private BookDtoMapper bookDtoMapper;

    @GetMapping()
    String emptyMapping() {
        return "Empty!";
    }

    @GetMapping("/")
    List<Book> getBookList() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable(required = false, name = "id") Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(bookDtoMapper.map(book));
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addbookpost")
    String addNewBook(@RequestParam String title,
                      @RequestParam int publicationYear,
                      @RequestParam String publisher,
                      @RequestParam String authorFirstName,
                      @RequestParam String authorLastName,
                      @RequestParam String ISBN) {
        bookService.addBook(new BookDto(title,
                Year.of(publicationYear), publisher, authorFirstName, authorLastName, ISBN));
        return "redirect:books";
    }
}
