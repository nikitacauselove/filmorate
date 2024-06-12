package com.example.backend.mapper;

import com.example.api.dto.DirectorDto;
import com.example.api.dto.FilmDto;
import com.example.api.dto.GenreDto;
import com.example.api.dto.MpaDto;
import com.example.backend.repository.entity.Genre;
import com.example.backend.repository.entity.Director;
import com.example.backend.repository.entity.Film;
import com.example.backend.repository.entity.Mpa;
import com.example.backend.service.DirectorService;
import com.example.backend.service.GenreService;
import com.example.backend.service.MpaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DirectorMapper.class, GenreMapper.class, MpaMapper.class})
public abstract class FilmMapper {

    @Autowired
    private DirectorService directorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private MpaService mpaService;

    @Mapping(target = "mpa", qualifiedByName = "setMpa")
    @Mapping(target = "likesAmount", constant = "0")
    @Mapping(target = "genres", qualifiedByName = "setGenres")
    @Mapping(target = "likingUsers", expression = "java(java.util.Collections.emptySet())")
    @Mapping(target = "directors", qualifiedByName = "setDirectors")
    public abstract Film mapToFilm(FilmDto filmDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mpa", qualifiedByName = "setMpa")
    @Mapping(target = "likesAmount", ignore = true)
    @Mapping(target = "genres", qualifiedByName = "setGenres")
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "directors", qualifiedByName = "setDirectors")
    public abstract Film updateFilm(FilmDto filmDto, @MappingTarget Film film);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "sortGenresById")
    public abstract FilmDto mapToFilmDto(Film film);

    public abstract List<FilmDto> mapToFilmDto(List<Film> filmList);

    @Named("sortGenresById")
    public Set<Genre> sortGenresById(Set<Genre> genreSet) {
        return new TreeSet<>(genreSet);
    }

    @Named("setMpa")
    public Mpa setMpa(MpaDto mpaDto) {
        return mpaService.findById(mpaDto.id());
    }

    @Named("setGenres")
    public Set<Genre> setGenres(Set<GenreDto> genreDtoSet) {
        return genreDtoSet == null ? Collections.emptySet() : genreDtoSet.stream().map(genreDto -> genreService.findById(genreDto.id())).collect(Collectors.toSet());
    }

    @Named("setDirectors")
    public Set<Director> setDirectors(Set<DirectorDto> directorDtoSet) {
        return directorDtoSet == null ? Collections.emptySet() : directorDtoSet.stream().map(directorDto -> directorService.findById(directorDto.id())).collect(Collectors.toSet());
    }
}
