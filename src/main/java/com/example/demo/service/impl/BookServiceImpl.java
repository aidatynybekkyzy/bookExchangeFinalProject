package com.example.demo.service.impl;

import com.example.demo.data.BookRepository;
import com.example.demo.entity.Book;
import com.example.demo.exceptions.BookAlreadyExists;
import com.example.demo.model.Message;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Message addBook(Book bookComing, Long userId) {
        List<Book> userBooks = List.of(bookRepository.findBookByUserId(userId));

        Optional<Book> existingBook = userBooks.stream()
                .filter(book -> isBookExist(book).test(book))
                .findFirst();
        if (existingBook.isPresent()) {
            throw new BookAlreadyExists("The same book already exists");
        } else {
            bookRepository.save(bookComing);
        }
        return new Message("Book was saved.");
    }

    private Predicate<Book> isBookExist(Book bookComing) {
        return book -> book.getName().equals(bookComing.getName());
    }

    @Override
    public Book findBookByUserId(Long userId) {

        return bookRepository.findBookByUserId(userId);
    }


    @Override
    public Book getBookById(Long id) {
        for (Book b : bookRepository.findAll()) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
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
    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        return bookRepository.findBooksByNameAndAuthor(name, author);
    }
}
