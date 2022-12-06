package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageService {
    public String upload(Book book, MultipartFile multipartFile);

    Optional<Image> getImageByBookId(Long bookId);

    void deleteById(Long id);
}
