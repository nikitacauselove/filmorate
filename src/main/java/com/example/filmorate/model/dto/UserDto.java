package com.example.filmorate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private final Integer id;

    @NotNull(message = "Электронная почта пользователя не может быть пустой.")
    private final String email;

    @NotNull(message = "Login пользователя не может быть пустым.")
    private final String login;
    private final String name;

    @NotNull(message = "Дата рождения пользователя не может быть пустой.")
    private final LocalDate birthday;
    private final List<Integer> friends;
}
