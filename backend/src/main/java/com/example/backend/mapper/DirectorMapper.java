package com.example.backend.mapper;

import com.example.api.dto.Director;
import com.example.api.dto.DirectorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    Director toDirector(DirectorDto directorDto);

    @Mapping(target = "id", source = "director.id")
    @Mapping(target = "name", source = "directorDto.name")
    Director toDirector(DirectorDto directorDto, Director director);

    DirectorDto toDirectorDto(Director director);

    List<DirectorDto> toDirectorDto(List<Director> directors);
}
