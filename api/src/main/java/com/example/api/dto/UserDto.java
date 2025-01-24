package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Schema(description = "Информация о пользователе")
public record UserDto(@Schema(description = "Идентификатор пользователя") Long id,
                      @Schema(description = "Электронная почта пользователя") @Email String email,
                      @Schema(description = "Идентификатор учётной записи пользователя") @NotBlank String login,
                      @Schema(description = "Имя пользователя") String name,
                      @Schema(description = "День рождения пользователя") @Past LocalDate birthday) {
}
