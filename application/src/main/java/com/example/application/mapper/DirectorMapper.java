package com.example.application.mapper;

import com.example.api.dto.DirectorDto;
import com.example.application.repository.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @Mapping(target = "id", ignore = true)
    Director toDirector(DirectorDto directorDto);

    Director updateDirector(DirectorDto directorDto, @MappingTarget Director director);

    DirectorDto toDirectorDto(Director director);

    List<DirectorDto> toDirectorDto(List<Director> directorList);
}
