package com.example.demo.service;

import com.example.demo.dto.BookCreationDto;
import com.example.demo.dto.BookDetailsDto;
import com.example.demo.dto.BookMainInfoDto;
import com.example.demo.entity.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BookService {

    Message addBook(BookCreationDto bookComing, MultipartFile multipartFile, Long userId);
    BookDetailsDto getBookById(Long id);
    List<BookMainInfoDto> getAllBooksByUserId(Long userId);
    Message updateBook(Long bookId, BookCreationDto updatableBookDto);
    Message deleteBook(Long id);
    List<BookMainInfoDto> getLastAddedBooks(int countOfBook);

}
