package com.example.demo.service;

import com.example.demo.entity.Genre;
import com.example.demo.model.Message;

import java.util.List;

public interface GenreService {
    Message addGenre(Genre genre);
    List<Genre> getAllGenres();
    Genre getGenre(Long id);
    Message updateGenre(Genre genre);
    Message deleteGenre(Long id);
}
