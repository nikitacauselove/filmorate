package com.example.application.persistence.mapper;

import com.example.application.domain.Mpa;
import com.example.application.persistence.model.MpaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MpaEntityMapper {

    MpaEntity toEntity(Mpa mpa);

    Mpa toDomain(MpaEntity mpaEntity);

    List<Mpa> toDomain(List<MpaEntity> mpaEntityList);
}
