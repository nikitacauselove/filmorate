package com.example.filmorate.controller;

import com.example.filmorate.entity.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "genres")
public class GenresController {
    @GetMapping("/{id}")
    public Genre findById(@PathVariable int id) {
        return Genre.findById(id);
    }

    @GetMapping
    public List<Genre> findAll() {
        return List.of(Genre.values());
    }
}
