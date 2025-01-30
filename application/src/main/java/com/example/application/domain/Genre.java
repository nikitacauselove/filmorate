package com.example.application.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Genre(Long id, String name) implements Comparable<Genre> {

    @Override
    public int compareTo(Genre genre) {
        return Long.compare(this.id, genre.id);
    }
}
