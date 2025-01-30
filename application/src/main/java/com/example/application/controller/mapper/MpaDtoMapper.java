package com.example.application.controller.mapper;

import com.example.api.model.MpaDto;
import com.example.application.domain.Mpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MpaDtoMapper {

    MpaDto toDto(Mpa mpa);

    List<MpaDto> toDto(List<Mpa> mpaList);
}
