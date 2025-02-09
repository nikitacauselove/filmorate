package com.example.application.mapper;

import com.example.application.entity.SortBy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SortByMapper {

    SortBy toEntity(com.example.api.model.SortBy sortBy);
}
