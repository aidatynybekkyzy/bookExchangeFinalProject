package com.example.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//TODO add image

@Entity
@Table(name = "a_book")
public class Book extends BaseEntity<Long> {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    @Column(name = "book_author")
    private String author;


    @ManyToOne
    @JoinColumn(name = "genre_id") //TODO  добавить join /
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Book() {
    }

    public Book(String name, String description, String author, Genre genre) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
