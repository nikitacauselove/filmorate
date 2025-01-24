package com.example.api.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AfterReleaseDateOfFirstFilmValidator implements ConstraintValidator<AfterReleaseDateOfFirstFilm, LocalDate> {

    public static final String MESSAGE = "должно быть после 28 декабря 1895 года";
    public static final LocalDate RELEASE_DATE_OF_FIRST_FILM = LocalDate.of(1895, 12, 28);

    @Override
    public boolean isValid(LocalDate releaseDate, ConstraintValidatorContext context) {
        return releaseDate.isAfter(RELEASE_DATE_OF_FIRST_FILM);
    }
}
