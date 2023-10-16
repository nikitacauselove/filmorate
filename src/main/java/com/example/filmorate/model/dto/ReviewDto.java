package com.example.filmorate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {
    private final Integer reviewId;

    @NotNull
    private final String content;

    @NotNull
    private final Boolean isPositive;

    @NotNull
    private final Integer userId;

    @NotNull
    private final Integer filmId;
    private final Integer useful;
}
