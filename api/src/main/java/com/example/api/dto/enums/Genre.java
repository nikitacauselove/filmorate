package com.example.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Objects;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum Genre {
    COMEDY(1L, "Комедия"),
    DRAMA(2L, "Драма"),
    CARTOON(3L, "Мультфильм"),
    THRILLER(4L, "Триллер"),
    DOCUMENTARY(5L, "Документальный"),
    ACTION(6L, "Боевик");

    private final Long id;
    private final String name;

    @JsonCreator
    public static Genre findById(@JsonProperty("id") Long id) {
        return Arrays.stream(Genre.values())
                .filter(genre -> Objects.equals(genre.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр фильма с указанным идентификатором не найден."));
    }
}
