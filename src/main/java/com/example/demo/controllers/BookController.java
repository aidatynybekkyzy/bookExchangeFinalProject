package com.example.demo.controllers;

import com.example.demo.dto.BookCreationDto;
import com.example.demo.dto.BookDetailsDto;
import com.example.demo.entity.Message;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("exchange/books")
public class BookController {
    private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //TODO CHANGE TP OBJECT
    @PostMapping("/save")
    public ResponseEntity<Message> addBook(@RequestPart BookCreationDto bookCreationDto,
                                           @RequestPart MultipartFile imageFile,
                                           @RequestParam Long userId) {
        LOGGER.info("Inside addBook method of BookController");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/exchange/books/save").toString());
        return ResponseEntity.created(uri).body(bookService.addBook(bookCreationDto, imageFile, userId));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDetailsDto> getBookById(@PathVariable("bookId") Long id) {
        LOGGER.info("Inside getBookId method of BookController");
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<Message> updateBook(@PathVariable Long bookId,
                                              @RequestBody BookCreationDto bookCreationDto) {
        LOGGER.info("Inside updateBook method of BookController");
        return new ResponseEntity<>(bookService.updateBook(bookId, bookCreationDto), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Message> deleteBook(@PathVariable Long bookId) {
        LOGGER.info("Inside deleteBook method of BookController");
        return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
    }



}
