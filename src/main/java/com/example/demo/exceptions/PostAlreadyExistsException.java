package com.example.demo.exceptions;

public class PostAlreadyExistsException extends RuntimeException {
    public PostAlreadyExistsException(String the_same_post_already_exists) {
        super(the_same_post_already_exists);
    }
}
