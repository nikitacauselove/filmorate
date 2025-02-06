package com.example.application.mapper;

import com.example.api.model.FilmDto;
import com.example.application.entity.Director;
import com.example.application.entity.Film;
import com.example.application.entity.Genre;
import com.example.application.entity.Mpa;
import com.example.application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Mapper(componentModel = "spring", uses = {DirectorMapper.class, GenreMapper.class, MpaMapper.class})
public interface FilmMapper {

    @Mapping(target = "id", source = "filmDto.id")
    @Mapping(target = "name", source = "filmDto.name")
    @Mapping(target = "mpa", source = "mpa")
    @Mapping(target = "genres", source = "genreSet")
    @Mapping(target = "directors", source = "directorSet")
    @Mapping(target = "likingUsers", source = "userSet")
    @Mapping(target = "likesAmount", constant = "0")
    Film toEntity(FilmDto filmDto, Mpa mpa, Set<Genre> genreSet, Set<Director> directorSet, Set<User> userSet);

    @Mapping(target = "id", source = "filmDto.id")
    @Mapping(target = "name", source = "filmDto.name")
    @Mapping(target = "mpa", source = "mpa")
    @Mapping(target = "genres", source = "genreSet")
    @Mapping(target = "directors", source = "directorSet")
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "likesAmount", ignore = true)
    Film updateEntity(FilmDto filmDto, Mpa mpa, Set<Genre> genreSet, Set<Director> directorSet, @MappingTarget Film film);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "sortById")
    FilmDto toDto(Film film);

    List<FilmDto> toDto(List<Film> filmList);

    @Named("sortById")
    default Set<Genre> sortById(Set<Genre> genreSet) {
        return new TreeSet<>(genreSet);
    }
}
