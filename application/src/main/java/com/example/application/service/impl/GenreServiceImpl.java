package com.example.application.service.impl;

import com.example.application.repository.GenreRepository;
import com.example.application.repository.entity.Genre;
import com.example.application.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр фильма с указанным идентификатором не найден"));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
