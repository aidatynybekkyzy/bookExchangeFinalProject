package com.example.demo.service.impl;

import com.example.demo.data.GenreRepository;
import com.example.demo.entity.Genre;
import com.example.demo.model.Message;
import com.example.demo.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    GenreRepository genreRepository;

    @Override
    public Message addGenre(Genre genre) {
        genreRepository.save(genre);
        return new Message(" Genre was added");
    }

    @Override
    public List<Genre> getAllGenres() {

        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(Long id) {
        for (Genre genre : genreRepository.findAll()) {
            if (genre.getId().equals(id)) {
                return genreRepository.getOne(id);
            }
        }
        return null;
    }

    @Override
    public Message updateGenre(Genre genre) {
        for(Genre g: genreRepository.findAll()){
            if(g.getId().equals(genre.getId())){
                g.setName(genre.getName());
                genreRepository.save(g);
                return new Message("Genre is updated");
            }
        }
        return new Message("Genre with id " + genre.getId() + " does not exist");
    }

    @Override
    public Message deleteGenre(Long id) {
        for(Genre genre: genreRepository.findAll()){
            if(genre.getId().equals(id)){
                genreRepository.delete(genre);
                return new Message("Genre is removed");
            }
        }
        return new Message("Genre with id " + id + " does not exist");
    }
}
