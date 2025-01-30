package com.example.application.persistence.mapper;

import com.example.application.domain.Director;
import com.example.application.persistence.model.DirectorEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DirectorEntityMapper {

    @Mapping(target = "id", ignore = true)
    DirectorEntity toEntity(Director director);

    @Mapping(target = "id", ignore = true)
    DirectorEntity updateEntity(Director director, @MappingTarget DirectorEntity directorEntity);

    Director toDomain(DirectorEntity directorEntity);

    List<Director> toDomain(List<DirectorEntity> directorEntityList);

    @IterableMapping(qualifiedByName = "toId", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Iterable<Long> toIds(Set<Director> directorSet);

    @Named("toId")
    default Long toId(Director director) {
        return director.id();
    }
}
