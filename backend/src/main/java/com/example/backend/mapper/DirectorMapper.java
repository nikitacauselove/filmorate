package com.example.backend.mapper;

import com.example.api.dto.DirectorDto;
import com.example.backend.entity.Director;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @Mapping(target = "id", ignore = true)
    Director mapToDirectorIgnoreId(DirectorDto directorDto);

    @Named(value = "mapToDirector")
    Director mapToDirector(DirectorDto directorDto);

    @IterableMapping(qualifiedByName = "mapToDirector")
    Set<Director> mapToDirector(Set<DirectorDto> directorDtoList);

    @Mapping(target = "id", source = "director.id")
    @Mapping(target = "name", source = "directorDto.name")
    Director mapToDirector(DirectorDto directorDto, Director director);

    DirectorDto mapToDirectorDto(Director director);

    List<DirectorDto> mapToDirectorDto(List<Director> directorList);
}
