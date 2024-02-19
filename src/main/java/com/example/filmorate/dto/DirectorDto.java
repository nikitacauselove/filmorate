package com.example.filmorate.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class DirectorDto {
    private final Integer id;

    @NotNull(message = "Имя режиссера не может быть пустым.")
    private final String name;
}
