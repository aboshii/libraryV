package com.library.springlibrary;

import com.library.springlibrary.service.BookService;

public class LibraryControlFromConsole {
    private BookService bookService;


    public LibraryControlFromConsole(BookService bookService) {
        this.bookService = bookService;
    }


    private static enum LibraryControlOption {
        EXIT(0, "Exit application"),
        ADD_BOOK(1, "Add new book");

        private int option;
        private String description;

        LibraryControlOption(int option, String description) {
            this.option = option;
            this.description = description;
        }
    }
}
