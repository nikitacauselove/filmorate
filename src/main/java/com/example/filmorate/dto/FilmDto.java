package com.example.filmorate.dto;

import com.example.filmorate.entity.Director;
import com.example.filmorate.entity.Genre;
import com.example.filmorate.entity.Mpa;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {
    private final Integer id;

    @NotNull(message = "Название фильма не может быть пустым.")
    private final String name;

    @NotNull(message = "Описание фильма не может быть пустым.")
    private final String description;

    @NotNull(message = "Дата выхода фильма не может быть пустой.")
    private final LocalDate releaseDate;

    @NotNull(message = "Длительность фильма не может быть пустой.")
    private final Integer duration;

    @NotNull(message = "Жанр фильма не может быть пустым.")
    private final Mpa mpa;
    private final List<Genre> genres;
    private final List<Integer> likingUsers;
    private final List<Director> directors;
}
