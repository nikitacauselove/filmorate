package com.example.application.service.impl;

import com.example.application.entity.Genre;
import com.example.application.exception.NotFoundException;
import com.example.application.repository.GenreRepository;
import com.example.application.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GenreRepository.NOT_FOUND));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
