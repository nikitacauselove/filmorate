package com.example.application.domain;

import java.time.LocalDate;
import java.util.Set;

public record Film(Long id, String name, String description, LocalDate releaseDate, Integer duration, Mpa mpa,
                   Set<Genre> genres, Set<Director> directors, Set<User> likingUsers, Integer likesAmount) {
}
