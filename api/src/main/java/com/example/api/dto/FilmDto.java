package com.example.api.dto;

import com.example.api.constraint.AfterDateOfFirstFilm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Информация о фильме")
public record FilmDto(@Schema(description = "Идентификатор фильма") Long id,
                      @Schema(description = "Название фильма") @NotBlank String name,
                      @Schema(description = "Описание фильма") @Size(max = 200) String description,
                      @Schema(description = "Дата выхода фильма") @AfterDateOfFirstFilm @NotNull LocalDate releaseDate,
                      @Schema(description = "Длительность фильма") @Positive Integer duration,
                      @Schema(description = "Рейтинг Американской киноассоциации") @NotNull MpaDto mpa,
                      @Schema(description = "Список жанров фильма") Set<GenreDto> genres,
                      @Schema(description = "Список режиссёров фильма") Set<DirectorDto> directors) {
}