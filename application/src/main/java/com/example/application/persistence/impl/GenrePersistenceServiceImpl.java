package com.example.application.persistence.impl;

import com.example.application.domain.Genre;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.GenrePersistenceService;
import com.example.application.persistence.mapper.GenreEntityMapper;
import com.example.application.persistence.model.GenreEntity;
import com.example.application.persistence.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenrePersistenceServiceImpl implements GenrePersistenceService {

    private final GenreEntityMapper genreEntityMapper;
    private final GenreRepository genreRepository;

    @Override
    public Genre findById(Long id) {
        GenreEntity genreEntity =  genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GenreRepository.NOT_FOUND));

        return genreEntityMapper.toDomain(genreEntity);
    }

    @Override
    public List<Genre> findAll() {
        return genreEntityMapper.toDomain(genreRepository.findAll());
    }

    @Override
    public List<Genre> findAllById(Iterable<Long> ids) {
        return genreEntityMapper.toDomain(genreRepository.findAllById(ids));
    }
}
