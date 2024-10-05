package com.example.application.mapper;

import com.example.api.dto.MpaDto;
import com.example.application.repository.entity.Mpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MpaMapper {

    MpaDto toMpaDto(Mpa mpa);

    List<MpaDto> toMpaDto(List<Mpa> mpaList);
}
