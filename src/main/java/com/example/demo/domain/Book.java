package com.example.demo.domain;


import javax.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;


//TODO add image

@Getter
@Setter
@Entity(name ="Book")
@Table(name = "book")
public class Book  {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "isbn", unique = true)
    @Length(min = 10)
    private String ISBN;
    private String description;

    @Column(name = "book_author")
    private String author;
    private String publisher;
    @ManyToOne
    @JoinColumn(name = "genre_id") //TODO  добавить join /
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne( cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private BookImage bookImage;

    public Book( String name, String ISBN, String description, String author, String publisher, Genre genre, User user, BookImage bookImage) {
        this.name = name;
        this.ISBN = ISBN;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.user = user;
        this.bookImage = bookImage;
    }
    public Book( String name, String ISBN, String description, String author, String publisher, Genre genre  ) {
        this.name = name;
        this.ISBN = ISBN;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
    }

    public Book() {

    }
}
