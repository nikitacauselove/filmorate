package com.example.filmorate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Review {
    private Integer id;
    private final String content;
    private final Boolean isPositive;
    private final Integer userId;
    private final Integer filmId;
    private final Integer useful;

    public enum MarkType {
        DISLIKE,
        LIKE
    }
}
