package com.example.demo.service.impl;

import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PostOfBookRepository;
import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import com.example.demo.domain.PostOfBook;

import com.example.demo.exceptions.PostAlreadyExistsException;
import com.example.demo.model.Message;

import com.example.demo.service.GenreService;
import com.example.demo.service.PostOfBookService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class PostOfBookServiceImpl implements PostOfBookService {

    private PostOfBookRepository postOfBookRepository;
    private BookRepository bookRepository;
    private GenreService genreService;


    public PostOfBookServiceImpl(PostOfBookRepository postOfBookRepository) {
        this.postOfBookRepository = postOfBookRepository;
    }


    @Override
    public Message addPost(PostOfBook postBook, String username) {
        Genre genre = genreService.getGenre(1L);
        List<PostOfBook> posts = postOfBookRepository.findPostOfBookByUser_UsernameIgnoreCaseOrderByDatePosted(username);
        Optional<PostOfBook> existingPost = posts.stream()
                .filter(post -> isPostExists(postBook).test(post))
                .findFirst();
        if (existingPost.isPresent()) {
            throw new PostAlreadyExistsException("The same book already exists");
        }
        Book book = bookRepository.save(postBook.getBook());
        PostOfBook post = new PostOfBook(postBook.getTitle(),
                book, postBook.getDescription(), genre, postBook.getDatePosted());
        postOfBookRepository.save(post);
        return new Message("Post was successfully added");
    }

    @Override
    public PostOfBook getPostById(Long id) {
        for (PostOfBook post : postOfBookRepository.findAll()) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
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
    public Message updatePost(PostOfBook postBook) {
        return null;
    }

    @Override
    public List<PostOfBook> findPostOfBookByUser_UsernameIgnoreCaseOrderByDatePosted(String username) {
        return postOfBookRepository.findPostOfBookByUser_UsernameIgnoreCaseOrderByDatePosted(username);
    }


    private Predicate<PostOfBook> isPostExists(PostOfBook postComing) {
        return post -> post.getTitle().equals(postComing.getTitle());
    }
}
