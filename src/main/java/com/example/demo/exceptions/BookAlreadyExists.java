package com.example.demo.exceptions;

public class BookAlreadyExists extends RuntimeException {
    public BookAlreadyExists(String the_same_book_already_exists) {
        super(the_same_book_already_exists);
    }
}
