package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.domain.PostOfBook;
import com.example.demo.domain.User;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.PostAlreadyExistsException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Message;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PostOfBookRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookImageService;
import com.example.demo.service.PostOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class PostOfBookServiceImpl implements PostOfBookService {
    private PostOfBookRepository postOfBookRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private BookImageService imageService;


    public PostOfBookServiceImpl(PostOfBookRepository postOfBookRepository) {
        this.postOfBookRepository = postOfBookRepository;
    }


    @Override
    public Message addPost(PostOfBook postBook, Long id, MultipartFile image) {
        List<PostOfBook> posts = postOfBookRepository.findPostOfBooksByUser_IdOrderByDatePosted(id);
        Optional<PostOfBook> existingPost = posts
                .stream()
                .filter(post -> isPostExists(postBook).test(post))
                .findFirst();
        if (existingPost.isPresent()) {
            throw new PostAlreadyExistsException("The same book already exists");
        }
        User user = userRepository.findById(id).orElseThrow();
        Book book = bookRepository.save(postBook.getBook());
        PostOfBook post = new PostOfBook(postBook.getTitle(), book,
                postBook.getDescription(), postBook.getDatePosted(), postBook.getPublisher());
        book.setUser(user);
        postOfBookRepository.save(post);
        imageService.upload(book, image);

        return new Message("Post was successfully added");
    }

    @Override
    public PostOfBook getPostById(Long id) {
        Optional<PostOfBook> post = postOfBookRepository.findById(id);
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post with id %d not found" + id);
        }
        return post.get();
    }

    @Override
    public List<PostOfBook> getAllPosts() {
        return postOfBookRepository.findAll();
    }

    @Override
    public Message deletePost(Long id) {
        for (PostOfBook post : postOfBookRepository.findAll()) {
            if (post.getId().equals(id)) {
                postOfBookRepository.delete(post);
                return new Message("Post is removed");
            }
        }
        return new Message("Book with id " + id + " does not exist");
    }

    @Override
    public Message updatePost(Long postId, PostOfBook postBook) {
        PostOfBook toUpdatePost = postOfBookRepository.findById(postId).get();
        if (Objects.nonNull(postBook.getBook().getName()) && !"".equalsIgnoreCase(
                postOfBookRepository.findPostOfBooksByTitleIgnoreCase(toUpdatePost.getBook().getName()))) {
            Book book = bookRepository.save(postBook.getBook());
            toUpdatePost.setBook(book);
        }
        if (Objects.nonNull(postBook.getDescription()) && !"".equalsIgnoreCase(postBook.getDescription())) {
            toUpdatePost.setDescription(postBook.getDescription());
        }
        if (Objects.nonNull(postBook.getTitle()) && !"".equalsIgnoreCase(postBook.getTitle())) {
            toUpdatePost.setTitle(postBook.getTitle());
        }
        if (Objects.nonNull(postBook.getPublisher()) && !"".equalsIgnoreCase(postBook.getPublisher())) {
            toUpdatePost.setPublisher(postBook.getPublisher());
        }
        postOfBookRepository.save(toUpdatePost);
        return new Message("Post updated");
    }

    public List<PostOfBook> findPostByUser_Username(String username){
        return postOfBookRepository.findPostOfBooksByUser_UsernameOrderByDatePosted(username);
    }


    private Predicate<PostOfBook> isPostExists(PostOfBook postComing) {
        return post -> post.getTitle().equals(postComing.getTitle());
    }
}
