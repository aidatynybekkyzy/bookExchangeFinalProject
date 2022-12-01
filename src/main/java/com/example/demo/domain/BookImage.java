package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookImage {

    private String url;
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

}

