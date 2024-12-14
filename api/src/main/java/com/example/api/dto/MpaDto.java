package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о рейтинге Американской киноассоциации")
public record MpaDto(@Schema(description = "Идентификатор рейтинга Американской киноассоциации") Long id,
                     @Schema(description = "Название рейтинга Американской киноассоциации") String name) {
}
