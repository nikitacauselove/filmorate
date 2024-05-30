package com.example.api;

import com.example.api.dto.enums.Genre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/genres")
@Tag(name = "Жанры", description = "Взаимодействие с жанрами фильмов")
public interface GenreApi {

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о жанре")
    Genre findById(
            @PathVariable Long id
    );

    @GetMapping
    @Operation(description = "Получение списка всех жанров")
    List<Genre> findAll();
}
