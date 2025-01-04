package com.example.application.mapper;

import com.example.api.dto.GenreDto;
import com.example.application.repository.entity.Genre;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toGenreDto(Genre genre);

    List<GenreDto> toGenreDto(List<Genre> genreList);

    Set<GenreDto> toGenreDto(Set<Genre> genreSet);

    @IterableMapping(qualifiedByName = "toId", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Iterable<Long> toIds(Set<GenreDto> genreDtoSet);

    @Named("toId")
    default Long toId(GenreDto genreDto) {
        return genreDto.id();
    }
}
