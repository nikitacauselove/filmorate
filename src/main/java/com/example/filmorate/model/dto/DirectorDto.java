package com.example.filmorate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DirectorDto {
    private final Integer id;

    @NotNull(message = "Имя режиссера не может быть пустым.")
    private final String name;
}
