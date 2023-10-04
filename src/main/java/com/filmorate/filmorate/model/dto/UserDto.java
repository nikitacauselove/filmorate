package com.filmorate.filmorate.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private final Integer id;

    @NotNull
    private final String email;

    @NotNull
    private final String login;
    private final String name;

    @NotNull
    private final LocalDate birthday;
    private final List<Integer> friends;
}
