package com.example.application.persistence.mapper;

import com.example.application.domain.Film;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.persistence.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {DirectorEntityMapper.class, GenreEntityMapper.class, MpaEntityMapper.class})
public interface FilmEntityMapper {

    @Mapping(target = "id", source = "film.id")
    @Mapping(target = "name", source = "film.name")
    @Mapping(target = "directors", source = "directors")
    @Mapping(target = "likingUsers", source = "likingUsers")
    @Mapping(target = "likesAmount", constant = "0")
    FilmEntity toEntity(Film film, Set<DirectorEntity> directors, Set<UserEntity> likingUsers);

    @Mapping(target = "id", source = "film.id")
    @Mapping(target = "name", source = "film.name")
    @Mapping(target = "directors", source = "directors")
    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "likesAmount", ignore = true)
    FilmEntity updateEntity(Film film, Set<DirectorEntity> directors, @MappingTarget FilmEntity filmEntity);

    Film toDomain(FilmEntity filmEntity);

    List<Film> toDomain(List<FilmEntity> filmEntityList);
}
