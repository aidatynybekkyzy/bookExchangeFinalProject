package com.example.demo.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String the_same_book_already_exists) {
        super(the_same_book_already_exists);
    }
}
