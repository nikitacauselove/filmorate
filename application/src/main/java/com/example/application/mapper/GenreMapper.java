package com.example.application.mapper;

import com.example.api.model.GenreDto;
import com.example.application.entity.Genre;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    List<GenreDto> toDto(List<Genre> genreList);

    @IterableMapping(qualifiedByName = "toId", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Iterable<Long> toIds(Set<GenreDto> genreDtoSet);

    @Named("toId")
    default Long toId(GenreDto genreDto) {
        return genreDto.id();
    }
}
