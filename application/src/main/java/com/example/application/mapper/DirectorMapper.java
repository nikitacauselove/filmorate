package com.example.application.mapper;

import com.example.api.dto.DirectorDto;
import com.example.application.repository.entity.Director;
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
    Director toDirector(DirectorDto directorDto);

    Director updateDirector(DirectorDto directorDto, @MappingTarget Director director);

    DirectorDto toDirectorDto(Director director);

    List<DirectorDto> toDirectorDto(List<Director> directorList);

    @IterableMapping(qualifiedByName = "toId", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Iterable<Long> toIds(Set<DirectorDto> directorDtoSet);

    @Named("toId")
    default Long toId(DirectorDto directorDto) {
        return directorDto.id();
    }
}
