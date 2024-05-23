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
        List<Genre> genres = filmDto.genres() == null ? Collections.emptyList() : filmDto.genres();
        List<Director> directors = filmDto.directors() == null ? Collections.emptyList() : filmDto.directors();

        return new Film(null, filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), filmDto.mpa(), genres, Collections.emptyList(), directors);
    }

    public Film toFilm(FilmDto filmDto, Film film) {
        List<Genre> genres = filmDto.genres() == null ? Collections.emptyList() : filmDto.genres();
        List<Director> directors = filmDto.directors() == null ? Collections.emptyList() : filmDto.directors();

        return new Film(film.getId(), filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), filmDto.mpa(), genres, film.getLikingUsers(), directors);
    }

    public abstract FilmDto toFilmDto(Film film);

    public abstract List<FilmDto> toFilmDto(List<Film> films);
}
