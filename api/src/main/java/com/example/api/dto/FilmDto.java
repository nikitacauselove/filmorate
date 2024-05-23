package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Информация о фильме")
public record FilmDto(@Schema(description = "Идентификатор фильма") Integer id,
                      @Schema(description = "Название фильма") @NotNull String name,
                      @Schema(description = "Описание фильма") @NotNull String description,
                      @Schema(description = "Дата выхода фильма") @NotNull LocalDate releaseDate,
                      @Schema(description = "Длительность фильма") @NotNull Integer duration,
                      @Schema(description = "MPAA") @NotNull Mpa mpa,
                      @Schema(description = "Список жанров фильма") List<Genre> genres,
                      @Schema(description = "Список пользователей, которым понравился этот фильм") List<Integer> likingUsers,
                      @Schema(description = "Список режиссёров") List<Director> directors) {
}