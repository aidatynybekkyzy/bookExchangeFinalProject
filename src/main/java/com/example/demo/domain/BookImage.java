package com.example.demo.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity()
@Table(name = "book_image")
@Getter
@Setter
@NoArgsConstructor
public class BookImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookImage(String url, byte[] data, Book book) {
        this.url = url;
        this.data = data;
        this.book = book;
    }

}

