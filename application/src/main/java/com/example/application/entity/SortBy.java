package com.example.application.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Критерий сортировки фильмов.
 */
@Getter
@RequiredArgsConstructor
public enum SortBy {

    /**
     * Сортировка по количеству положительных оценок фильма.
     */
    LIKES(Film.Fields.likesAmount),

    /**
     * Сортировка по дате выхода фильма.
     */
    YEAR(Film.Fields.releaseDate);

    private final String property;
}
