package com.example.application.service;

import com.example.api.dto.FilmDto;
import com.example.application.repository.entity.Film;

import java.util.List;

public interface FilmService {

    Film create(FilmDto filmDto);

    Film update(FilmDto filmDto);

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
