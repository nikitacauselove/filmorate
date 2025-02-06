package com.example.application.mapper;

import com.example.api.model.MpaDto;
import com.example.application.entity.Mpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MpaMapper {

    MpaDto toDto(Mpa mpa);

    List<MpaDto> toDto(List<Mpa> mpaList);
}
