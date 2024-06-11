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
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private MpaService mpaService;

    public Film mapToFilm(FilmDto filmDto) {
        Set<Genre> genreSet = filmDto.genres() == null ? Collections.emptySet() : filmDto.genres().stream().map(genreDto -> genreService.findById(genreDto.id())).collect(Collectors.toSet());
        Set<Director> directorList = filmDto.directors() == null ? Collections.emptySet() : filmDto.directors().stream().map(directorDto -> directorService.findById(directorDto.id())).collect(Collectors.toSet());

        return new Film(null, filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(),  mpaService.findById(filmDto.mpa().id()), 0, genreSet, Collections.emptySet(), directorList);
    }

    public Film mapToFilm(FilmDto filmDto, Film film) {
        Set<Genre> genreSet = filmDto.genres() == null ? Collections.emptySet() : filmDto.genres().stream().map(genreDto -> genreService.findById(genreDto.id())).collect(Collectors.toSet());
        Set<Director> directorList = filmDto.directors() == null ? Collections.emptySet() : filmDto.directors().stream().map(directorDto -> directorService.findById(directorDto.id())).collect(Collectors.toSet());

        return new Film(film.getId(), filmDto.name(), filmDto.description(), filmDto.releaseDate(), filmDto.duration(), mpaService.findById(filmDto.mpa().id()), film.getLikesAmount(), genreSet, film.getLikingUsers(), directorList);
    }

    @Mapping(source = "genres", target = "genres", qualifiedByName = "test")
    public abstract FilmDto mapToFilmDto(Film film);

    public abstract List<FilmDto> mapToFilmDto(List<Film> filmList);

    @Named("test")
    public Set<GenreDto> test(Set<Genre> genreList) {
        Set<Genre> treeSet = new TreeSet<>(genreList);

        return genreMapper.mapToGenreDto(treeSet);
    }
}
