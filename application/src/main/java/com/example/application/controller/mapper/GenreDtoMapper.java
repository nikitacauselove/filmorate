package com.example.application.controller.mapper;

import com.example.api.model.GenreDto;
import com.example.application.domain.Genre;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreDtoMapper {

    GenreDto toDto(Genre genre);

    List<GenreDto> toDto(List<Genre> genreList);

    Set<GenreDto> toDto(Set<Genre> genreSet);
}
