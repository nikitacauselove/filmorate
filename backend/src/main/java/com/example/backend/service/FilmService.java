package com.example.backend.service;

import com.example.backend.entity.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    Film create(Film film);

    Film update(Film film);

    Film findById(int id);

    List<Film> findAll();

    List<Film> findAllByDirectorId(int directorId, Film.SortBy sortBy);

    void deleteById(int filmId);

    void addLike(int id, int userId);

    void deleteLike(int id, int userId);

    List<Film> findCommon(int userId, int friendId);

    List<Film> findPopular(int count, Optional<Integer> genreId, Optional<Integer> year);

    List<Film> search(String query, List<String> by);
}
