package com.example.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DirectorDto {
    private final Integer id;

    @NotNull
    private final String name;
}
