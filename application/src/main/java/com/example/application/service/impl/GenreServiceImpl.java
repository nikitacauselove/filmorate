package com.example.application.service.impl;

import com.example.application.domain.Genre;
import com.example.application.persistence.GenrePersistenceService;
import com.example.application.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenrePersistenceService genrePersistenceService;

    @Override
    public Genre findById(Long id) {
        return genrePersistenceService.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genrePersistenceService.findAll();
    }
}
