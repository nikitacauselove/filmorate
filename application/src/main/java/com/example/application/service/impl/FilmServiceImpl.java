package com.example.application.service.impl;

import com.example.application.domain.By;
import com.example.application.domain.Event;
import com.example.application.domain.EventType;
import com.example.application.domain.Film;
import com.example.application.domain.Operation;
import com.example.application.domain.SortBy;
import com.example.application.persistence.EventPersistenceService;
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

    private final EventPersistenceService eventPersistenceService;
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
    @Transactional
    public void addLike(Long id, Long userId) {
        filmPersistenceService.addLike(id, userId);
        eventPersistenceService.create(Event.builder()
                .userId(userId)
                .eventType(EventType.LIKE)
                .operation(Operation.ADD)
                .entityId(id)
                .build());
    }

    @Override
    @Transactional
    public void deleteLike(Long id, Long userId) {
        filmPersistenceService.deleteLike(id, userId);
        eventPersistenceService.create(Event.builder()
                .userId(userId)
                .eventType(EventType.LIKE)
                .operation(Operation.REMOVE)
                .entityId(id)
                .build());
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
    public List<Film> findRecommendations(Long userId) {
        return filmPersistenceService.findRecommendations(userId);
    }

    @Override
    public List<Film> search(String query, List<By> by) {
        return filmPersistenceService.search(query, by);
    }
}
