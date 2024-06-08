package com.example.backend.mapper;

import com.example.api.dto.FilmDto;
import com.example.api.dto.GenreDto;
import com.example.backend.repository.entity.Genre;
import com.example.backend.repository.entity.Director;
import com.example.backend.repository.entity.Film;
import com.example.backend.service.DirectorService;
import com.example.backend.service.GenreService;
import com.example.backend.service.MpaService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {

    @Autowired
    private DirectorMapper directorMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private MpaMapper mpaMapper;

    @Autowired
    private GenreService genreService;
    @Autowired
    private MpaService mpaService;

    public Film mapToFilm(FilmDto filmDto) {
        Set<Genre> genreSet = filmDto.genres() == null ? Collections.emptySet() : filmDto.genres().stream().map(genreDto -> genreService.findById(genreDto.id())).collect(Collectors.toSet());
        Set<Director> directorList = filmDto.directors() == null ? Collections.emptySet() : directorMapper.mapToDirector(filmDto.directors());

        return new Film(null, filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(),  mpaService.findById(filmDto.mpa().id()), 0, genreSet, Collections.emptySet(), directorList);
    }

    public Film mapToFilm(FilmDto filmDto, Film film) {
        Set<Genre> genreSet = filmDto.genres() == null ? Collections.emptySet() : filmDto.genres().stream().map(genreDto -> genreService.findById(genreDto.id())).collect(Collectors.toSet());
        Set<Director> directorList = filmDto.directors() == null ? Collections.emptySet() : directorMapper.mapToDirector(filmDto.directors());

        return new Film(film.getId(), filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), mpaService.findById(filmDto.mpa().id()), film.getLikesAmount(), genreSet, film.getLikingUsers(), directorList);
    }

    public abstract FilmDto mapToFilmDto(Film film);

    public abstract List<FilmDto> mapToFilmDto(List<Film> filmList);
}
