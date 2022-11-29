package com.example.demo.exceptions;

public class PostAlreadyExists extends RuntimeException {
    public PostAlreadyExists(String the_same_post_already_exists) {
        super(the_same_post_already_exists);
    }
}
