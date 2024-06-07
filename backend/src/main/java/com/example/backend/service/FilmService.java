package com.example.backend.service;

import com.example.backend.repository.entity.Film;

import java.util.List;

public interface FilmService {

    Film create(Film film);

    Film update(Film film);

    Film findById(Long id);

    List<Film> findAll();

    List<Film> findAllByDirectorId(Long directorId, Film.SortBy sortBy);

    void deleteById(Long id);

    void addLike(Long id, Long userId);

    void deleteLike(Long id, Long userId);

    List<Film> findCommon(Long userId, Long friendId);

    List<Film> findPopular(Integer count, Long genreId, Integer year);

    List<Film> search(String query, List<String> by);
}
