package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "a_book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please enter book name")
    private String title;
    @Column(name = "isbn", unique = true)
    @Length(min = 10)
    private String ISBN;
    @Column(name = "book_author")
    @NotBlank
    private String author;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genre;
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    public Book(String title, String ISBN, String author,
                Set<Genre> genre, String description, String publisher,
                User user) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.user = user;
    }

}
