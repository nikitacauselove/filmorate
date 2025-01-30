package com.example.application.persistence.mapper;

import com.example.application.domain.Film;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.persistence.model.GenreEntity;
import com.example.application.persistence.model.MpaEntity;
import com.example.application.persistence.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {DirectorEntityMapper.class, GenreEntityMapper.class, MpaEntityMapper.class})
public interface FilmEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "film.name")
    @Mapping(target = "mpa", source = "mpa")
    @Mapping(target = "genres", source = "genreSet")
    @Mapping(target = "directors", source = "directorSet")
    @Mapping(target = "likesAmount", constant = "0")
    FilmEntity toEntity(Film film, MpaEntity mpa, Set<GenreEntity> genreSet, Set<DirectorEntity> directorSet, Set<UserEntity> likingUsers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "film.name")
    @Mapping(target = "mpa", source = "mpa")
    @Mapping(target = "genres", source = "genreSet")
    @Mapping(target = "directors", source = "directorSet")
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "likesAmount", ignore = true)
    FilmEntity updateEntity(Film film, MpaEntity mpa, Set<GenreEntity> genreSet, Set<DirectorEntity> directorSet, @MappingTarget FilmEntity filmEntity);

    Film toDomain(FilmEntity filmEntity);

    List<Film> toDomain(List<FilmEntity> filmEntityList);
}
