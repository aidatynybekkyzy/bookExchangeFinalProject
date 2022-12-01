package com.example.demo.service;

import com.example.demo.domain.PostOfBook;
import com.example.demo.model.Message;

import java.util.List;

public interface PostOfBookService {
    Message addPost(PostOfBook postOfBook, String username);
    PostOfBook getPostById(Long id);
    List<PostOfBook> getAllPosts();

    Message deletePost(Long id);
    Message updatePost(PostOfBook postBook);
    List<PostOfBook> findPostOfBookByUser_UsernameIgnoreCaseOrderByDatePosted(String username);

}
