package com.example.application.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record User(Long id, String email, String login, String name, LocalDate birthday) {
}
