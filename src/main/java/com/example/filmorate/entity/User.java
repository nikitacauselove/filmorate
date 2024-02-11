package com.example.filmorate.entity;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private final String email;
    private final String login;
    private final String name;
    private final LocalDate birthday;
    private final List<Integer> friends;

    public boolean isValid() {
        if (!email.contains("@") || email.contains(" ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Электронная почта не может быть пустой и должна содержать символ @.");
        }
        if (login.contains(" ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Логин не может быть пустым и содержать пробелы.");
        }
        if (birthday.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Дата рождения не может быть в будущем.");
        }
        return true;
    }
}
