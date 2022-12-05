package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


//TODO add image

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
    private String name;
    @Column(name = "isbn", unique = true)
    @Length(min = 10)
    private String ISBN;
    @Column(name = "book_author")
    @NotBlank
    private String author;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"))
    private Set<Genre> genre;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "book")
    private BookImage bookImage;

    public Book(String name, String ISBN, String author,
                Set<Genre> genre, String description, String publisher,
                User user ) {
        this.name = name;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.user = user;
    }
}
