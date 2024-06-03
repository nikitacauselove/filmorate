package com.example.api.dto;

import com.example.api.constraints.AfterDateOfFirstFilm;
import com.example.api.dto.enums.Genre;
import com.example.api.dto.enums.Mpa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Schema(description = "Информация о фильме")
public record FilmDto(@Schema(description = "Идентификатор фильма") Long id,
                      @Schema(description = "Название фильма") @NotBlank String name,
                      @Schema(description = "Описание фильма") @Size(max = 200) String description,
                      @Schema(description = "Дата выхода фильма") @NotNull @AfterDateOfFirstFilm LocalDate releaseDate,
                      @Schema(description = "Длительность фильма") @Positive Integer duration,
                      @Schema(description = "MPAA") @NotNull Mpa mpa,
                      @Schema(description = "Список жанров фильма") Set<Genre> genres) {
}