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
        return "bookpage.html";
    }

//    @PostMapping("/addbook")
//    @ResponseBody
//    void addNewBook(@RequestParam String title,
//                    @RequestParam int publicationYear,
//                    @RequestParam String publisher,
//                    @RequestParam String authorFirstName,
//                    @RequestParam String authorLastName,
//                    @RequestParam String ISBN){
//        bookService.addBook(new BookDto(title,
//                Year.of(publicationYear), publisher, authorFirstName, authorLastName, ISBN));
//    }
}
