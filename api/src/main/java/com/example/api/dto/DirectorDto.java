package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Информация о режиссёре")
public record DirectorDto(@Schema(description = "Идентификатор режиссёра") Long id,
                          @Schema(description = "Имя режиссёра") @NotBlank String name) {
}
