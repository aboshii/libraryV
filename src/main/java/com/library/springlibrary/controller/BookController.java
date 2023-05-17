package com.library.springlibrary.controller;

import java.time.Year;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.PublicationComment;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.PublicationCommentDto;
import com.library.springlibrary.service.BookService;
import com.library.springlibrary.service.VisitCounter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private VisitCounter visitCounter;

    @GetMapping("/")
    String mainPage(){
        visitCounter.visitCounterCountUp();
        return "index";
    }

    @GetMapping("/book")
    String book(@RequestParam(required = false, defaultValue = "1") String id, Model model) {
        Long idN = Long.parseLong(id);
        Book book = bookService.getBookById(idN);
        model.addAttribute("book", book);
        model.addAttribute("comment", new PublicationComment());
        if (book.getBorrower() != null) {
            model.addAttribute("borrowerFirstName", book.getBorrower().getFirstName());
            model.addAttribute("borrowerLastName", book.getBorrower().getLastName());
        }
        return "bookpage.html";
    }

    @GetMapping("/books")
    String getBookList(Model model){
        model.addAttribute("books", bookService.getBooks());
        return "booklist.html";
    }

    @GetMapping("/addbook")
    String addNewBook(){
        return "addbook.html";
    }
    @PostMapping("/addbookpost")
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
                .fromPath("redirect:book")
                .queryParam("id", id)
                .build()
                .toString();
        //response.sendRedirect("/book?id=" + id);
        //return book(Long.toString(id), model);
    }
}