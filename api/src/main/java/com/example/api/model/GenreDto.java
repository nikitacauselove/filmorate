package com.example.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
@Schema(description = "Информация о жанре фильма")
public record GenreDto(@Schema(description = "Идентификатор жанра фильма") Long id,
                       @Schema(description = "Название жанра фильма") String name) {
}
