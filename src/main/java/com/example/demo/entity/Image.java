package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "book_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id", nullable = false)
    private Long id;
    private String url;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Image(String url, byte[] data, Book book) {
        this.url = url;
        this.data = data;
        this.book = book;
    }
}

