package com.example.demo.service;

import com.example.demo.data.PostOfBookRepository;
import com.example.demo.entity.PostOfBook;
import com.example.demo.model.Message;

import java.util.List;

public interface PostOfBookService {
    Message addPost(PostOfBook postOfBook, Long userId);
    PostOfBook getPostById(Long id);
    List<PostOfBook> getAllPosts();

    Message deletePost(Long id);
    Message updatePost(PostOfBook postBook);
    List<PostOfBook> getPostsOfUser(String userLogin);
    List<PostOfBook> getPostsByBookNameAuthor(String author, String bookName);
}
