package com.example.application.persistence.mapper;

import com.example.application.domain.Genre;
import com.example.application.persistence.model.GenreEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreEntityMapper {

    GenreEntity toEntity(Genre genre);

    Genre toDomain(GenreEntity genreEntity);

    List<Genre> toDomain(List<GenreEntity> genreEntityList);

    @IterableMapping(qualifiedByName = "toId", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Iterable<Long> toIds(Set<Genre> genreSet);

    @Named("toId")
    default Long toId(Genre genre) {
        return genre.id();
    }
}
