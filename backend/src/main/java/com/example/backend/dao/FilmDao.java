package com.example.backend.dao;

import com.example.api.dto.Director;
import com.example.api.dto.Genre;
import com.example.backend.entity.Film;

import java.util.List;
import java.util.Optional;

public interface FilmDao {

    void create(Film film);

    void update(Film film);

    Film findById(int id);

    boolean existsById(int id);

    List<Film> findAll();

    List<Film> findAllByDirectorId(int directorId, Film.SortBy sortBy);

    void deleteById(int filmId);

    void addLike(int id, int userId);

    void deleteLike(int id, int userId);

    List<Film> findCommon(int userId, int friendId);

    List<Film> findPopular(int count, Optional<Integer> genreId, Optional<Integer> year);

    List<Film> findRecommendations(int id);

    List<Film> search(String query, List<String> by);

    List<Genre> findGenresByFilmId(int filmId);

    List<Integer> findLikingUsersByFilmId(int filmId);

    List<Director> findDirectorsByFilmId(int filmId);
}
