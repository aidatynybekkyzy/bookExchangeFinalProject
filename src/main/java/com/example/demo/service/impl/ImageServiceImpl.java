package com.example.demo.service.impl;

import com.example.demo.mappers.BookDetailsMapper;
import com.example.demo.entity.Book;
import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    BookDetailsMapper bookDetailsMapper;

    @Override
    public String upload(Book book, MultipartFile multipartFile) {
        String imageUrl = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            imageRepository.save(new Image(imageUrl, multipartFile.getBytes(), book));
        } catch (IOException e) {
            throw new MessageDescriptorFormatException("File hasn't been saved");
        }
        return imageUrl;
    }

    @Override
    public Optional<Image> getImageByBookId(Long bookId) {
        return Optional.ofNullable(imageRepository.findImageByBook_Id(bookId));
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }
}
