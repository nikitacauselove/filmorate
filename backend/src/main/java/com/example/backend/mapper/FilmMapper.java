package com.example.backend.mapper;

import com.example.api.dto.Director;
import com.example.api.dto.FilmDto;
import com.example.api.dto.Genre;
import com.example.backend.entity.Film;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {

    public Film toFilm(FilmDto filmDto) {
        List<Genre> genres = filmDto.getGenres() == null ? Collections.emptyList() : filmDto.getGenres();
        List<Director> directors = filmDto.getDirectors() == null ? Collections.emptyList() : filmDto.getDirectors();

        return new Film(null, filmDto.getName(), filmDto.getDescription(), filmDto.getReleaseDate(), filmDto.getDuration(), filmDto.getMpa(), genres, Collections.emptyList(), directors);
    }

    public Film toFilm(FilmDto filmDto, Film film) {
        List<Genre> genres = filmDto.getGenres() == null ? Collections.emptyList() : filmDto.getGenres();
        List<Director> directors = filmDto.getDirectors() == null ? Collections.emptyList() : filmDto.getDirectors();

        return new Film(film.getId(), filmDto.getName(), filmDto.getDescription(), filmDto.getReleaseDate(), filmDto.getDuration(), filmDto.getMpa(), genres, film.getLikingUsers(), directors);
    }

    public abstract FilmDto toFilmDto(Film film);

    public abstract List<FilmDto> toFilmDto(List<Film> films);
}
