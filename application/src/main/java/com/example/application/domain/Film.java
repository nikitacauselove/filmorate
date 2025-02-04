package com.example.application.domain;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder(toBuilder = true)
public record Film(Long id, String name, String description, LocalDate releaseDate, Integer duration, Mpa mpa,
                   Set<Genre> genres, Set<Director> directors) {
}
