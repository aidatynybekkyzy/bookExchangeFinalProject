package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.dto.BookCreateDto;
import com.example.demo.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BookService {

    Message addBook(BookCreateDto bookComing, MultipartFile multipartFile,Long userId);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    Book updateBook(Long bookId, Book book);

    Message deleteBook(Long id);

    Book getBookByName(String bookName);

    List<Book> getBookByISBN(String isbn);
}
