package com.example.demo.service.impl;

import com.example.demo.mappers.BookCreationMapper;
import com.example.demo.mappers.BookDetailsMapper;
import com.example.demo.mappers.BookMainInfoMapper;
import com.example.demo.dto.BookCreationDto;
import com.example.demo.dto.BookDetailsDto;
import com.example.demo.dto.BookMainInfoDto;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.exception.BookAlreadyExistsException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.entity.Book;
import com.example.demo.entity.Message;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ImageService bookImageService;
    private final BookCreationMapper bookCreationMapper;
    private final BookDetailsMapper bookDetailsMapper;
    private final BookMainInfoMapper bookMainInfoMapper;


    @Override @Transactional
    public Message addBook(BookCreationDto bookCreationDto, MultipartFile image, Long userId) {
        List<Book> books = bookRepository.findBooksByUserId(userId);

        Optional<Book> existingBook = books.stream()
                .filter(book -> isBookExist(bookCreationDto).test(book))
                .findFirst();
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("The same book already exists");
        }

        User user = userRepository.findById(userId).orElseThrow();
        Book book = Optional.of(bookCreationDto)
                .map(bookCreationMapper::toEntity).orElseThrow();
        book.setUser(user);

        bookRepository.save(book);
        bookImageService.upload(book, image);

        return new Message("New Book added");
    }

    @Override
    public BookDetailsDto getBookById(Long id) {
        BookDetailsDto bookDetailsDto = Optional.ofNullable(bookRepository.findBookByUserId(id))
                .map(bookDetailsMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException("Book does not exist with id = " + id));
        Optional<Image> bookImage = bookImageService.getImageByBookId(id);
        bookImage.ifPresent(image -> bookDetailsDto.setImageData(image.getData()));
        return bookDetailsDto;
    }

    @Override
    public List<BookMainInfoDto> getAllBooksByUserId(Long userId) {
        return bookRepository.findBooksByUserId(userId)
                .stream()
                .map(bookMainInfoMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Message updateBook(Long bookId, BookCreationDto updatableBookDto) {
        for (Book updatableBook : bookRepository.findAll()) {
            if (updatableBook.getId().equals(updatableBookDto.getId())) {
                updatableBook.setTitle(updatableBookDto.getTitle());
                updatableBook.setISBN(updatableBookDto.getISBN());
                updatableBook.setAuthor(updatableBookDto.getAuthor());
                updatableBook.setGenre(updatableBookDto.getGenre());
                updatableBook.setDescription(updatableBookDto.getDescription());
                return new Message("Book is updated");
            }
        }
        return new Message("Book with id does not exist" + updatableBookDto.getId());
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
    public List<BookMainInfoDto> getLastAddedBooks(int countOfBook) {
        return bookRepository.getLastAddedBooks(countOfBook).stream()
                .map(bookMainInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    private Predicate<Book> isBookExist(BookCreationDto bookCreationDto) {
        return book2 -> book2.getISBN().equals(bookCreationDto.getISBN())
                && book2.getTitle().equals(bookCreationDto.getTitle());
    }

}
