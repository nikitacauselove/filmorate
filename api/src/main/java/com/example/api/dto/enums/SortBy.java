package com.example.api.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Критерий поиска.
 */
@Getter
@RequiredArgsConstructor
public enum SortBy {

    /**
     * Количество положительных оценок фильма.
     */
    LIKES("likesAmount"),

    /**
     * Дата выхода фильма.
     */
    YEAR("releaseDate");

    private final String criteria;
}
