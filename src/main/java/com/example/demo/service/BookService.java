package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.model.Message;

import java.util.List;

public interface BookService {
    Message addBook(Book book, Long user_id);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    Message updateBook(Book book);

    Message deleteBook(Long id);

    Book findBookByUserId(Long userId);

    List<Book> getBooksByNameAndAuthor(String name, String author);
}
