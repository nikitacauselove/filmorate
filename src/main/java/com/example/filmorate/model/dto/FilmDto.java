package com.example.filmorate.model.dto;

import com.example.filmorate.model.Director;
import com.example.filmorate.model.Genre;
import com.example.filmorate.model.Mpa;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {
    private final Integer id;

    @NotNull
    private final String name;

    @NotNull
    private final String description;

    @NotNull
    private final LocalDate releaseDate;

    @NotNull
    private final Integer duration;

    @NotNull
    private final Mpa mpa;
    private final List<Genre> genres;
    private final List<Integer> likingUsers;
    private final List<Director> directors;
}