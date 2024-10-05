package com.example.api.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AfterDateOfFirstFilmValidator implements ConstraintValidator<AfterDateOfFirstFilm, LocalDate> {

    public static final LocalDate DATE_OF_FIRST_FILM = LocalDate.of(1895, 12, 28);
    public static final String MESSAGE = "должно быть после 28 декабря 1895 года";

    @Override
    public boolean isValid(LocalDate releaseDate, ConstraintValidatorContext context) {
        return releaseDate.isAfter(DATE_OF_FIRST_FILM);
    }
}
