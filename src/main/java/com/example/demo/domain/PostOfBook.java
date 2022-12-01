package com.example.demo.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "PostOfBook")
@Table(name ="book_post")

public class PostOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

  /*  @ManyToOne
    @JoinColumn(name = "genre_id") //TODO  добавить join /
    private Genre genre;*/


    @CreationTimestamp
    private LocalDateTime datePosted;

    public PostOfBook() {
    }

    public PostOfBook(String title, Book book, String description, Genre genre, LocalDateTime datePosted) {
        this.title = title;
        this.book = book;
        this.description = description;
        //this.genre = genre;
        this.datePosted = datePosted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }
}
