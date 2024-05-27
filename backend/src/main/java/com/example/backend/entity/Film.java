package com.example.backend.entity;

import com.example.api.dto.Genre;
import com.example.api.dto.Mpa;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class Film {
    private Integer id;
    private final String name;
    private final String description;
    private final LocalDate releaseDate;
    private final Integer duration;
    private final Mpa mpa;
    private final List<Genre> genres;
    private final List<Integer> likingUsers;
    private final List<Director> directors;

    private static final LocalDate DATE_OF_FIRST_FILM = LocalDate.of(1895, 12, 28);

    public enum SortBy {
        YEAR,
        LIKES;

        public static SortBy from(String string) {
            for (SortBy value : SortBy.values()) {
                if (value.name().equals(string)) {
                    return value;
                }
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Указанный тип сортировки не найден.");
        }
    }

    public boolean isValid() {
        if (name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Название не может быть пустым.");
        }
        if (200 < description.length()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Максимальная длина описания — 200 символов.");
        }
        if (releaseDate.isBefore(DATE_OF_FIRST_FILM)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Дата релиза — не раньше 28 декабря 1895 года.");
        }
        if (duration < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Продолжительность фильма должна быть положительной.");
        }
        return true;
    }
}
