package com.example.application.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Director(Long id, String name) {
}
