package com.example.application.controller.mapper;

import com.example.api.model.DirectorDto;
import com.example.application.domain.Director;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorDtoMapper {

    Director toDomain(DirectorDto directorDto);

    DirectorDto toDto(Director director);

    List<DirectorDto> toDto(List<Director> directorList);
}
