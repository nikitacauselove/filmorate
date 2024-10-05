package com.example.application.service;

import com.example.application.repository.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre findById(Long id);

    List<Genre> findAll();

    List<Genre> findAllById(Iterable<Long> ids);
}
