package com.example.backend.mapper;

import com.example.api.dto.FilmDto;
import com.example.api.dto.enums.Genre;
import com.example.backend.repository.entity.Director;
import com.example.backend.repository.entity.Film;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {

    @Autowired
    private DirectorMapper directorMapper;

    public Film mapToFilm(FilmDto filmDto) {
        Set<Genre> genreList = filmDto.genres() == null ? Collections.emptySet() : filmDto.genres();
        Set<Director> directorList = filmDto.directors() == null ? Collections.emptySet() : directorMapper.mapToDirector(filmDto.directors());

        return new Film(null, filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), filmDto.mpa(), 0, genreList, Collections.emptySet(), directorList);
    }

    public Film mapToFilm(FilmDto filmDto, Film film) {
        Set<Genre> genreList = filmDto.genres() == null ? Collections.emptySet() : filmDto.genres();
        Set<Director> directorList = filmDto.directors() == null ? Collections.emptySet() : directorMapper.mapToDirector(filmDto.directors());

        return new Film(film.getId(), filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), filmDto.mpa(), film.getLikesAmount(), genreList, film.getLikingUsers(), directorList);
    }

    public abstract FilmDto mapToFilmDto(Film film);

    public abstract List<FilmDto> mapToFilmDto(List<Film> filmList);
}
