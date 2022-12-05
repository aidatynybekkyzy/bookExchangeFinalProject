package com.example.demo.service.impl;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.domain.Book;
import com.example.demo.model.Message;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookImageService;
import com.example.demo.service.BookService;
import com.example.demo.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookImageService bookImageService;


    @Override
    public Message addBook(BookCreateDto bookComing, MultipartFile image, Long userId) {

        return new Message("Impossible to add");
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with id %d not found" + id);
        }
        return book.get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Long bookId, Book book) {

        Book toUpdateBook = bookRepository.findById(bookId).get();
        if (Objects.nonNull(book.getName()) && !"".equalsIgnoreCase(book.getName())) {
            toUpdateBook.setName(book.getName());
        }
        if (Objects.nonNull(book.getISBN()) && !"".equalsIgnoreCase(book.getISBN())) {
            toUpdateBook.setISBN(book.getISBN());
        }
        if (Objects.nonNull(book.getGenre()) && !"".equalsIgnoreCase(book.getGenre().toString())) {
            toUpdateBook.setGenre(book.getGenre());
        }
        if (Objects.nonNull(book.getDescription()) && !"".equalsIgnoreCase(book.getDescription())) {
            toUpdateBook.setDescription(book.getDescription());
        }
        if (Objects.nonNull(book.getAuthor()) && !"".equalsIgnoreCase(book.getAuthor())) {
            toUpdateBook.setAuthor(book.getAuthor());
        }

        return bookRepository.save(toUpdateBook);
    }

    @Override
    public Message deleteBook(Long id) {
        for (Book b : bookRepository.findAll()) {
            if (b.getId().equals(id)) {
                bookRepository.delete(b);
                return new Message("Book is removed");
            }
        }
        return new Message("Book with id " + id + " does not exist");

    }

    @Override
    public Book getBookByName(String bookName) {
        return bookRepository.getBookByNameIgnoreCase(bookName);
    }

    @Override
    public List<Book> getBookByISBN(String isbn) {
        return bookRepository.getBooksByISBN(isbn);
    }

}
