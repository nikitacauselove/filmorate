package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private final String email;
    private final String login;
    private final String name;
    private final LocalDate birthday;
    private final List<Long> friends;
}
