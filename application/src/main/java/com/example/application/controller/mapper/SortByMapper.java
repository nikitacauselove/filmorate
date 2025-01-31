package com.example.application.controller.mapper;

import com.example.application.domain.SortBy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SortByMapper {

    SortBy toDomain(com.example.api.model.SortBy sortBy);
}
