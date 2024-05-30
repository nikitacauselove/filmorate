package com.example.backend.service;

import com.example.backend.entity.Film;

import java.util.List;
import java.util.Optional;

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

    List<Film> findPopular(Integer count, Optional<Long> genreId, Optional<Integer> year);

    List<Film> search(String query, List<String> by);
}
