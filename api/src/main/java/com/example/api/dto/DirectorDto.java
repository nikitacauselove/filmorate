package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Информация о режиссёре")
public record DirectorDto(@Schema(description = "Идентификатор режиссёра") Integer id,
                          @Schema(description = "Имя режиссёра") @NotBlank String name) {
}
