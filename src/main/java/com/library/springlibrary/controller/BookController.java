package com.library.springlibrary.controller;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.Year;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @GetMapping("/book")
    String book(@RequestParam(required = false, defaultValue = "1") String id, Model model) {
        Long idN = Long.parseLong(id);
        Book book = bookService.getBookById(idN);
        System.out.println("padu");
        model.addAttribute("book", book);
        if (book.getBorrower() != null) {
            model.addAttribute("borrowerFirstName", book.getBorrower().getFirstName());
            model.addAttribute("borrowerLastName", book.getBorrower().getLastName());
        }
        return "bookpage.html";
    }

    @GetMapping("books")
    String getBookList(Model model){
        model.addAttribute("books", bookService.getBooks());
        return "booklist.html";
    }

    @GetMapping("/addbook")
    String addNewBook(){
        return "addbook.html";
    }
    @PostMapping("/addbookpost")
    @ResponseBody
    void addNewBook(@RequestParam String title,
                    @RequestParam int publicationYear,
                    @RequestParam String publisher,
                    @RequestParam String authorFirstName,
                    @RequestParam String authorLastName,
                    @RequestParam String ISBN){
        bookService.addBook(new BookDto(title,
                Year.of(publicationYear), publisher, authorFirstName, authorLastName, ISBN));
    }
}
