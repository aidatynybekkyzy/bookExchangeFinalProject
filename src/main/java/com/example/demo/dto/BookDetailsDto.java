package com.example.demo.dto;

import com.example.demo.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsDto {
    private Long id;
    private String title;
    private String ISBN;
    private String author;
    private Set<Genre> genre;
    private String description;
    private String publisher;
    private byte[] imageData;
}
