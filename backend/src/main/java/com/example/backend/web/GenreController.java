package com.example.backend.web;

import com.example.api.GenreApi;
import com.example.api.dto.Genre;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController implements GenreApi {

    public Genre findById(Integer id) {
        return Genre.findById(id);
    }

    public List<Genre> findAll() {
        return List.of(Genre.values());
    }
}
