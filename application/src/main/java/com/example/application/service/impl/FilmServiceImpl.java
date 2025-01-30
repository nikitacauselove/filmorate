package com.example.application.service.impl;

import com.example.api.model.type.By;
import com.example.api.model.type.Operation;
import com.example.api.model.type.SortBy;
import com.example.application.domain.Film;
import com.example.application.persistence.FilmPersistenceService;
import com.example.application.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmPersistenceService filmPersistenceService;

    @Override
    public Film create(Film film) {
        return filmPersistenceService.create(film);
    }

    @Override
    public Film update(Film film) {
        return filmPersistenceService.update(film);
    }

    @Override
    public Film findById(Long id) {
        return filmPersistenceService.findById(id);
    }

    @Override
    public List<Film> findAll() {
        return filmPersistenceService.findAll();
    }

    @Override
    public List<Film> findAllByDirectorId(Long directorId, SortBy sortBy) {
        return filmPersistenceService.findAllByDirectorId(directorId, sortBy);
    }

    @Override
    public void deleteById(Long id) {
        filmPersistenceService.deleteById(id);
    }

    @Override
    public void addOrDeleteLike(Long id, Long userId, Operation operation) {
        filmPersistenceService.addOrDeleteLike(id, userId, operation);
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
        return filmPersistenceService.findCommon(userId, friendId);
    }

    @Override
    public List<Film> findPopular(Integer count, Long genreId, Integer year) {
        return filmPersistenceService.findPopular(count, genreId, year);
    }

    @Override
    public List<Film> search(String query, List<By> by) {
        return filmPersistenceService.search(query, by);
    }
}
