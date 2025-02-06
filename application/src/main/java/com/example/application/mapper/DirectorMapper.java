package com.example.application.mapper;

import com.example.api.model.DirectorDto;
import com.example.application.entity.Director;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @Mapping(target = "id", ignore = true)
    Director toEntity(DirectorDto directorDto);

    Director updateEntity(DirectorDto directorDto, @MappingTarget Director director);

    DirectorDto toDto(Director director);

    List<DirectorDto> toDto(List<Director> directorList);

    @IterableMapping(qualifiedByName = "toId", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Iterable<Long> toIds(Set<DirectorDto> directorDtoSet);

    @Named("toId")
    default Long toId(DirectorDto directorDto) {
        return directorDto.id();
    }
}
