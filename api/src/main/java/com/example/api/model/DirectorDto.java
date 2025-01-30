package com.example.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder(toBuilder = true)
@Schema(description = "Информация о режиссёре")
public record DirectorDto(@Schema(description = "Идентификатор режиссёра") Long id,
                          @Schema(description = "Имя режиссёра") @NotBlank String name) {
}
