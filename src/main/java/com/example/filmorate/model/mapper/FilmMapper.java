package com.example.filmorate.model.mapper;

import com.example.filmorate.model.Director;
import com.example.filmorate.model.Film;
import com.example.filmorate.model.Genre;
import com.example.filmorate.model.dto.FilmDto;

import java.util.*;
import java.util.stream.Collectors;

public class FilmMapper {
    public static Film toFilm(FilmDto filmDto) {
        List<Genre> genres = filmDto.getGenres() == null ? Collections.emptyList() : filmDto.getGenres();
        List<Director> directors = filmDto.getDirectors() == null ? Collections.emptyList() : filmDto.getDirectors();

        return new Film(null, filmDto.getName(), filmDto.getDescription(), filmDto.getReleaseDate(), filmDto.getDuration(), filmDto.getMpa(), genres, Collections.emptyList(), directors);
    }

    public static Film toFilm(FilmDto filmDto, Film film) {
        List<Genre> genres = filmDto.getGenres() == null ? Collections.emptyList() : filmDto.getGenres();
        List<Director> directors = filmDto.getDirectors() == null ? Collections.emptyList() : filmDto.getDirectors();

        return new Film(film.getId(), filmDto.getName(), filmDto.getDescription(), filmDto.getReleaseDate(), filmDto.getDuration(), filmDto.getMpa(), genres, film.getLikingUsers(), directors);
    }

    public static FilmDto toFilmDto(Film film) {
        return new FilmDto(film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(), film.getMpa(), film.getGenres(), film.getLikingUsers(), film.getDirectors());
    }

    public static List<FilmDto> toFilmDto(List<Film> films) {
        return films.stream()
                .map(FilmMapper::toFilmDto)
                .collect(Collectors.toList());
    }
}
