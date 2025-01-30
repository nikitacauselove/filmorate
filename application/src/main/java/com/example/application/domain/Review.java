package com.example.application.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Review(Long id, String content, Boolean isPositive, Long userId, Long filmId, Integer useful) {
}
