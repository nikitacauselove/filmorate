package com.example.filmorate.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class ReviewDto {
    private final Integer reviewId;

    @NotNull(message = "Содержимое отзыва не может быть пустым.")
    private final String content;

    @NotNull(message = "Оценка отзыва не может быть пустой.")
    private final Boolean isPositive;

    @NotNull(message = "Идентификатор пользователя не может быть пустым.")
    private final Integer userId;

    @NotNull(message = "Идентификатор фильма не может быть пустым.")
    private final Integer filmId;
    private final Integer useful;
}
