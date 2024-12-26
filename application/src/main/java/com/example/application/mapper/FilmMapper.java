package com.example.application.mapper;

import com.example.api.dto.FilmDto;
import com.example.application.mapper.decorator.FilmMapperDecorator;
import com.example.application.repository.entity.Director;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Genre;
import com.example.application.repository.entity.Mpa;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@DecoratedWith(FilmMapperDecorator.class)
@Mapper(componentModel = "spring", uses = {DirectorMapper.class, GenreMapper.class, MpaMapper.class})
public interface FilmMapper {

    @Mapping(target = "id", source = "filmDto.id")
    @Mapping(target = "name", source = "filmDto.name")
    @Mapping(target = "mpa", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "directors", ignore = true)
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "likesAmount", constant = "0")
    Film toFilm(FilmDto filmDto, Mpa mpa, Set<Genre> genreSet, Set<Director> directorSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "filmDto.name")
    @Mapping(target = "mpa", source = "mpa")
    @Mapping(target = "genres", source = "genreSet")
    @Mapping(target = "directors", source = "directorSet")
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "likesAmount", ignore = true)
    Film updateFilm(FilmDto filmDto, Mpa mpa, Set<Genre> genreSet, Set<Director> directorSet, @MappingTarget Film film);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "sortGenresById")
    FilmDto toFilmDto(Film film);

    List<FilmDto> toFilmDto(List<Film> filmList);

    @Named("sortGenresById")
    default Set<Genre> sortGenresById(Set<Genre> genreSet) {
        return new TreeSet<>(genreSet);
    }
}
