package com.filmorate.filmorate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DirectorDto {
    private final Integer id;

    @NotNull
    private final String name;
}
