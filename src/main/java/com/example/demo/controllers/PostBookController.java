package com.example.demo.controllers;

import com.example.demo.domain.PostOfBook;
import com.example.demo.model.Message;
import com.example.demo.repository.PostOfBookRepository;
import com.example.demo.service.PostOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/exchange/posts")
public class PostBookController {
    PostOfBookService postOfBookService;
    PostOfBookRepository postOfBookRepository;

    @Autowired
    public PostBookController(PostOfBookService postOfBookService, PostOfBookRepository postOfBookRepository) {
        this.postOfBookService = postOfBookService;
        this.postOfBookRepository = postOfBookRepository;
    }

    @GetMapping("all")
    public ResponseEntity<List<PostOfBook>> getAllBooks() {
        return new ResponseEntity<>(postOfBookService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createPost(@RequestParam Long postId,
                                              @RequestPart PostOfBook bookPost,
                                              @RequestPart MultipartFile image) {
        postOfBookService.addPost(bookPost, postId, image);
        return new ResponseEntity<>(new Message("Post created"), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePost(@PathVariable Long postId, PostOfBook post) {
        postOfBookService.updatePost(postId, post);
        return new ResponseEntity<>(new Message("Post updated"), HttpStatus.OK);
    }

    @GetMapping("/userposts")
    public ResponseEntity<Message> findPostsByUser_Username(@RequestParam String username) {
        postOfBookService.findPostByUser_Username(username);
        return new ResponseEntity<>(new Message("Catalog of post: "), HttpStatus.OK);
    }


}
