package com.example.api.dto;

import com.example.api.constraints.AfterDateOfFirstFilm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Информация о фильме")
public record FilmDto(@Schema(description = "Идентификатор фильма") Integer id,
                      @Schema(description = "Название фильма") @NotBlank String name,
                      @Schema(description = "Описание фильма") @Size(max = 200) String description,
                      @Schema(description = "Дата выхода фильма") @NotNull @AfterDateOfFirstFilm LocalDate releaseDate,
                      @Schema(description = "Длительность фильма") @Positive Integer duration,
                      @Schema(description = "MPAA") @NotNull Mpa mpa,
                      @Schema(description = "Список жанров фильма") List<Genre> genres,
                      @Schema(description = "Список пользователей, которым понравился этот фильм") List<Integer> likingUsers,
                      @Schema(description = "Список режиссёров") List<DirectorDto> directors) {
}