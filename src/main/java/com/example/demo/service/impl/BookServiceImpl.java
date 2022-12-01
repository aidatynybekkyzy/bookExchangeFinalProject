package com.example.demo.service.impl;

import com.example.demo.mapper.MapStructMapper;
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

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookImageService bookImageService;

    private final MapStructMapper mapStructMapper;

    @Override
    public Message addBook(BookCreateDto bookComing, MultipartFile image,Long userId) {
        if (bookComing!= null || image!= null) {
            bookRepository.save(mapStructMapper.toEntity(bookComing));
            bookImageService.upload(mapStructMapper.toEntity(bookComing), image);
            return new Message("new book saved");
        }
        return new Message("Impossible to add");
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Message updateBook(Book book) {
        for (Book b : bookRepository.findAll()) {
            if (b.getId().equals(book.getId())) {
                b.setName(book.getName());
                b.setAuthor(book.getAuthor());
                b.setDescription(book.getDescription());
                bookRepository.save(b);
                return new Message("Book is updated");
            }
        }
        return new Message("Book with id " + book.getId() + " does not exist");
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
    public List<Book> getBookByName(String bookName) {
        return bookRepository.getBookByNameIgnoreCase(bookName);
    }

    @Override
    public List<Book> getBooksByISBN(String isbn) {
        return bookRepository.getBooksByISBN(isbn);
    }

}
