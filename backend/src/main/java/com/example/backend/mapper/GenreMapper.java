package com.example.backend.mapper;

import com.example.api.dto.GenreDto;
import com.example.backend.repository.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toGenreDto(Genre genre);

    Set<GenreDto> toGenreDto(Set<Genre> genreSet);

    List<GenreDto> toGenreDto(List<Genre> genreList);
}
