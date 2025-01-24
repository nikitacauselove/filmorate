package com.example.api.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Критерий сортировки.
 */
@Getter
@RequiredArgsConstructor
public enum SortBy {

    /**
     * Сортировка по количеству положительных оценок фильма.
     */
    LIKES("likesAmount"),

    /**
     * Сортировка по дате выхода фильма.
     */
    YEAR("releaseDate");

    private final String criteria;
}
