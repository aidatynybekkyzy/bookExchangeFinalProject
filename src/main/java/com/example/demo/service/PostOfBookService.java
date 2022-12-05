package com.example.demo.service;

import com.example.demo.domain.PostOfBook;
import com.example.demo.model.Message;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostOfBookService {
    Message addPost(PostOfBook postOfBook, Long id, MultipartFile image);

    PostOfBook getPostById(Long id);

    List<PostOfBook> getAllPosts();

    Message deletePost(Long id);

    Message updatePost(Long id, PostOfBook postBook);

    List<PostOfBook> findPostByUser_Username(String username);
}
