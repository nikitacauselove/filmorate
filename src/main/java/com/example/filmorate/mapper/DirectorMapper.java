package com.example.filmorate.mapper;

import com.example.filmorate.dto.DirectorDto;
import com.example.filmorate.entity.Director;
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
