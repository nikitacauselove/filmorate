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
public enum Mpa {
    G(1L, "G"),
    PG(2L, "PG"),
    PG13(3L, "PG-13"),
    R(4L, "R"),
    NC17(5L, "NC-17");

    private final Long id;
    private final String name;

    @JsonCreator
    public static Mpa findById(@JsonProperty("id") Long id) {
        return Arrays.stream(Mpa.values())
                .filter(mpa -> Objects.equals(mpa.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейтинг Ассоциации кинокомпаний с указанным идентификатором не найден."));
    }
}
