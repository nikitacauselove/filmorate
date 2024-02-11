package com.example.filmorate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Data
public class Director {
    private Integer id;
    private final String name;

    public boolean isValid() {
        if (name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Имя режиссера не может быть пустым.");
        }
        return true;
    }
}
