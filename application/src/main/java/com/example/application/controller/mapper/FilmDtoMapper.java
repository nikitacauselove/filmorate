package com.example.application.controller.mapper;

import com.example.api.model.FilmDto;
import com.example.application.domain.Film;
import com.example.application.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Mapper(componentModel = "spring", uses = {DirectorDtoMapper.class, GenreDtoMapper.class, MpaDtoMapper.class})
public interface FilmDtoMapper {

    @Mapping(target = "likingUsers", ignore = true)
    @Mapping(target = "likesAmount", ignore = true)
    Film toDomain(FilmDto filmDto);

    @Mapping(target = "genres", source = "genres", qualifiedByName = "sortById")
    FilmDto toDto(Film film);

    List<FilmDto> toDto(List<Film> filmList);

    @Named("sortById")
    default Set<Genre> sortById(Set<Genre> genreSet) {
        return new TreeSet<>(genreSet);
    }
}
