package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о рейтинге Американской киноассоциации")
public record MpaDto(@Schema(description = "Идентификатор рейтинга") Long id,
                     @Schema(description = "Название рейтинга") String name) {
}
