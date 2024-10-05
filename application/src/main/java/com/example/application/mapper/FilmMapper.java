package com.example.application.mapper;

import com.example.api.dto.FilmDto;
import com.example.application.mapper.decorator.FilmMapperDecorator;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Genre;
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

    @Mapping(target = "mpa", ignore = true)
    @Mapping(target = "likesAmount", constant = "0")
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "directors", ignore = true)
    Film toFilm(FilmDto filmDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mpa", ignore = true)
    @Mapping(target = "likesAmount", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "directors", ignore = true)
    Film updateFilm(FilmDto filmDto, @MappingTarget Film film);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "sortGenresById")
    FilmDto toFilmDto(Film film);

    List<FilmDto> toFilmDto(List<Film> filmList);

    @Named("sortGenresById")
    default Set<Genre> sortGenresById(Set<Genre> genreSet) {
        return new TreeSet<>(genreSet);
    }
}
