package com.example.backend.dao;

import com.example.api.dto.enums.Genre;
import com.example.backend.entity.Director;
import com.example.backend.entity.Film;

import java.util.List;
import java.util.Optional;

public interface FilmDao {

    void create(Film film);

    void update(Film film);

    Film findById(Long id);

    boolean existsById(Long id);

    List<Film> findAll();

    List<Film> findAllByDirectorId(Long directorId, Film.SortBy sortBy);

    void deleteById(Long id);

    void addLike(Long id, Long userId);

    void deleteLike(Long id, Long userId);

    List<Film> findCommon(Long userId, Long friendId);

    List<Film> findPopular(Integer count, Optional<Long> genreId, Optional<Integer> year);

    List<Film> findRecommendations(Long id);

    List<Film> search(String query, List<String> by);

    List<Genre> findGenresByFilmId(Long filmId);

    List<Long> findLikingUsersByFilmId(Long filmId);

    List<Director> findDirectorsByFilmId(Long filmId);
}
