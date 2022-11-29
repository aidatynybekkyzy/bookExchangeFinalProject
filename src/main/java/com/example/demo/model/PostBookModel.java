package com.example.demo.model;

import com.example.demo.entity.Book;

public class PostBookModel {
    private Book book;
    private String terms;

    public PostBookModel() {
    }

    public PostBookModel(Book book, String terms) {
        this.book = book;
        this.terms = terms;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
