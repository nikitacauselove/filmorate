package com.example.backend.mapper;

import com.example.api.dto.DirectorDto;
import com.example.backend.repository.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @Mapping(target = "id", ignore = true)
    Director mapToDirectorIgnoreId(DirectorDto directorDto);

    Director updateDirector(DirectorDto directorDto, @MappingTarget Director director);

    DirectorDto mapToDirectorDto(Director director);

    List<DirectorDto> mapToDirectorDto(List<Director> directorList);
}
