package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о жанре фильма")
public record GenreDto(@Schema(description = "Идентификатор жанра фильма") Long id,
                       @Schema(description = "Название жанра фильма") String name) {
}
