package com.example.filmorate.mapper;

import com.example.filmorate.entity.Director;
import com.example.filmorate.dto.DirectorDto;

import java.util.List;
import java.util.stream.Collectors;

public class DirectorMapper {
    public static Director toDirector(DirectorDto directorDto) {
        return new Director(null, directorDto.getName());
    }

    public static Director toDirector(DirectorDto directorDto, Director director) {
        return new Director(director.getId(), directorDto.getName());
    }

    public static DirectorDto toDirectorDto(Director director) {
        return new DirectorDto(director.getId(), director.getName());
    }

    public static List<DirectorDto> toDirectorDto(List<Director> directors) {
        return directors.stream()
                .map(DirectorMapper::toDirectorDto)
                .collect(Collectors.toList());
    }
}
