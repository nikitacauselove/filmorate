package com.example.application.controller.mapper;

import com.example.application.domain.By;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ByMapper {

    By toDomain(com.example.api.model.By by);

    List<By> toDomain(List<com.example.api.model.By> byList);
}
