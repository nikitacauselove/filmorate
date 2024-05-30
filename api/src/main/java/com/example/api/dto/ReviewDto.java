package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Информация о рецензии")
public record ReviewDto(@Schema(description = "Идентификатор рецензии") Long reviewId,
                        @Schema(description = "Содержимое рецензии") @NotBlank String content,
                        @Schema(description = "Является ли рецензия положительной") @NotNull Boolean isPositive,
                        @Schema(description = "Идентификатор пользователя") @NotNull Long userId,
                        @Schema(description = "Идентификатор фильма") @NotNull Long filmId,
                        @Schema(description = "Количество пользователей, посчитавших рецензию полезной") Integer useful) {
}
