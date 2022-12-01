package com.example.demo.controllers;

import com.example.demo.domain.PostOfBook;
import com.example.demo.repository.PostOfBookRepository;
import com.example.demo.service.PostOfBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange/posts")
public class PostBookController {
    PostOfBookService postOfBookService;
    PostOfBookRepository postOfBookRepository;

    @GetMapping("/user/all")
    public ResponseEntity<List<PostOfBook>> getAllBooks() {
        return new ResponseEntity<>(postOfBookService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/by_username/{username}")
    public ResponseEntity<List<PostOfBook>> getPostsByUsername(@PathVariable("username") String  username) {
        return new ResponseEntity<>(postOfBookRepository.findPostOfBookByUser_UsernameIgnoreCaseOrderByDatePosted(username), HttpStatus.OK);
    }


}
