package com.example.api;

import com.example.api.dto.DirectorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/directors")
@Tag(name = "Режиссёры", description = "Взаимодействие с режиссёрами фильмов")
public interface DirectorApi {

    @PostMapping
    @Operation(description = "Добавление нового режиссёра")
    DirectorDto create(
            @RequestBody @Valid DirectorDto directorDto
    );

    @PutMapping
    @Operation(description = "Обновление информации о режиссёре")
    DirectorDto update(
            @RequestBody @Valid DirectorDto directorDto
    );

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о режиссёре")
    DirectorDto findById(
            @PathVariable Long id
    );

    @GetMapping
    @Operation(description = "Получение списка всех режиссёров")
    List<DirectorDto> findAll();

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление режиссёра")
    void deleteById(
            @PathVariable Long id
    );
}
