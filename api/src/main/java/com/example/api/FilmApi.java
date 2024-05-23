package com.example.api;

import com.example.api.dto.FilmDto;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/films")
@Tag(name = "Фильмы", description = "Взаимодействие с фильмами")
public interface FilmApi {

    @PostMapping
    @Operation(description = "Добавление нового фильма")
    FilmDto create(
            @RequestBody @Valid FilmDto filmDto
    );

    @PutMapping
    @Operation(description = "Обновление информации о фильме")
    FilmDto update(
            @RequestBody @Valid FilmDto filmDto
    );

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о фильме")
    FilmDto findById(
            @PathVariable Integer id
    );

    @GetMapping
    @Operation(description = "Получение списка всех фильмов")
    List<FilmDto> findAll();

    @GetMapping("/director/{directorId}")
    @Operation(description = "Получение списка всех фильмов определенного режиссёра")
    List<FilmDto> findAllByDirectorId(
            @PathVariable Integer directorId,
            @RequestParam String sortBy
    );

    @DeleteMapping("/{filmId}")
    @Operation(description = "Удаление фильма")
    void deleteById(
            @PathVariable Integer filmId
    );

    @PutMapping("/{id}/like/{userId}")
    @Operation(description = "Добавление реакции")
    FilmDto addLike(
            @PathVariable Integer id,
            @PathVariable Integer userId
    );

    @DeleteMapping("/{id}/like/{userId}")
    @Operation(description = "Удаление реакции")
    FilmDto deleteLike(
            @PathVariable Integer id,
            @PathVariable Integer userId
    );

    @GetMapping("/common")
    @Operation(description = "Получение списка общих фильмов")
    List<FilmDto> findCommon(
            @RequestParam Integer userId,
            @RequestParam Integer friendId
    );

    @GetMapping("/popular")
    @Operation(description = "Получение списка популярных фильмов")
    List<FilmDto> findPopular(
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam Optional<Integer> genreId,
            @RequestParam Optional<Integer> year
    );

    @GetMapping("/search")
    @Operation(description = "Поиск фильмов")
    List<FilmDto> search(
            @RequestParam String query,
            @RequestParam List<String> by
    );
}
