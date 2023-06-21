package com.library.springlibrary.exceptions;

public class UserCantBeAddedException extends RuntimeException{
    public UserCantBeAddedException() {
    }
    public UserCantBeAddedException(String message) {
        super(message);
    }
}
