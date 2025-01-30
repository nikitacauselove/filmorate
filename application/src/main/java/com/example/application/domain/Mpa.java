package com.example.application.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Mpa(Long id, String name) {
}
