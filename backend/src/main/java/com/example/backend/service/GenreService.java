package com.example.backend.service;

import com.example.backend.repository.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre findById(Long id);

    List<Genre> findAll();

    List<Genre> findAllById(Iterable<Long> ids);
}
