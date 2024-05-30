package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Review {
    private Long id;
    private final String content;
    private final Boolean isPositive;
    private final Long userId;
    private final Long filmId;
    private final Integer useful;

    public enum MarkType {
        DISLIKE,
        LIKE
    }
}
