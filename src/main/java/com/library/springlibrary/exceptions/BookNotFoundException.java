package com.library.springlibrary.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
    }
    public BookNotFoundException(String message) {
        super(message);
    }
}
