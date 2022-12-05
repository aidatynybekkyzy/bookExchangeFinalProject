package com.example.demo.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book_post")

public class PostOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private String description;
    @CreationTimestamp
    private LocalDateTime datePosted;
    @Column(name = "post_publisher")
    private String publisher;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "userId_post")
    private User user;

    public PostOfBook(String title, Book book, String description,
                       LocalDateTime datePosted, String publisher) {
        this.title = title;
        this.book = book;
        this.description = description;
        this.datePosted = LocalDateTime.now();
        this.publisher = publisher;
    }
}