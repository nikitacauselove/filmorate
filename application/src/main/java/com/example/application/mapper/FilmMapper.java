package com.example.application.mapper;

import com.example.api.dto.DirectorDto;
import com.example.api.dto.FilmDto;
import com.example.api.dto.GenreDto;
import com.example.api.dto.MpaDto;
import com.example.application.repository.entity.Genre;
import com.example.application.repository.entity.Director;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Mpa;
import com.example.application.service.DirectorService;
import com.example.application.service.GenreService;
import com.example.application.service.MpaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Mapper(componentModel = "spring", uses = {DirectorMapper.class, GenreMapper.class, MpaMapper.class})
public abstract class FilmMapper {

    @Autowired
    private DirectorService directorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private MpaService mpaService;

    @Mapping(target = "mpa", qualifiedByName = "findMpaById")
    @Mapping(target = "likesAmount", constant = "0")
    @Mapping(target = "genres", qualifiedByName = "findAllGenresById")
    @Mapping(target = "likingUsers", expression = "java(java.util.Collections.emptySet())")
    @Mapping(target = "directors", qualifiedByName = "findAllDirectorsById")
    public abstract Film toFilm(FilmDto filmDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mpa", qualifiedByName = "findMpaById")
    @Mapping(target = "likesAmount", ignore = true)
    @Mapping(target = "genres", qualifiedByName = "findAllGenresById")
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "directors", qualifiedByName = "findAllDirectorsById")
    public abstract Film updateFilm(FilmDto filmDto, @MappingTarget Film film);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "sortGenresById")
    public abstract FilmDto toFilmDto(Film film);

    public abstract List<FilmDto> toFilmDto(List<Film> filmList);

    @Named("findMpaById")
    protected Mpa findMpaById(MpaDto mpaDto) {
        return mpaService.findById(mpaDto.id());
    }

    @Named("findAllGenresById")
    protected Set<Genre> findAllGenresById(Set<GenreDto> genreDtoSet) {
        List<Long> ids = genreDtoSet == null ? Collections.emptyList() : genreDtoSet.stream().map(GenreDto::id).toList();

        return new HashSet<>(genreService.findAllById(ids));
    }

    @Named("findAllDirectorsById")
    protected Set<Director> findAllDirectorsById(Set<DirectorDto> directorDtoSet) {
        List<Long> ids = directorDtoSet == null ? Collections.emptyList() : directorDtoSet.stream().map(DirectorDto::id).toList();

        return new HashSet<>(directorService.findAllById(ids));
    }

    @Named("sortGenresById")
    protected Set<Genre> sortGenresById(Set<Genre> genreSet) {
        return new TreeSet<>(genreSet);
    }
}
