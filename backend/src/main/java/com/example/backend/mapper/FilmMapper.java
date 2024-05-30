package com.example.backend.mapper;

import com.example.api.dto.FilmDto;
import com.example.api.dto.enums.Genre;
import com.example.backend.entity.Director;
import com.example.backend.entity.Film;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {

    @Autowired
    private DirectorMapper directorMapper;

    public Film mapToFilm(FilmDto filmDto) {
        List<Genre> genreList = filmDto.genres() == null ? Collections.emptyList() : filmDto.genres();
        List<Director> directorList = filmDto.directors() == null ? Collections.emptyList() : directorMapper.mapToDirector(filmDto.directors());

        return new Film(null, filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), filmDto.mpa(), genreList, Collections.emptyList(), directorList);
    }

    public Film mapToFilm(FilmDto filmDto, Film film) {
        List<Genre> genreList = filmDto.genres() == null ? Collections.emptyList() : filmDto.genres();
        List<Director> directorList = filmDto.directors() == null ? Collections.emptyList() : directorMapper.mapToDirector(filmDto.directors());

        return new Film(film.getId(), filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), filmDto.mpa(), genreList, film.getLikingUsers(), directorList);
    }

    public abstract FilmDto mapToFilmDto(Film film);

    public abstract List<FilmDto> mapToFilmDto(List<Film> filmList);
}
