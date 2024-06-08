package com.example.backend.mapper;

import com.example.api.dto.MpaDto;
import com.example.backend.repository.entity.Mpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MpaMapper {

    Mpa mapToMpa(MpaDto mpaDto);

    MpaDto mapToMpaDto(Mpa mpa);

    List<MpaDto> mapToMpaDto(List<Mpa> mpaList);
}
