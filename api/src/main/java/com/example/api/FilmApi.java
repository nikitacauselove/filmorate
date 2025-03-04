package com.example.api;

import com.example.api.model.FilmDto;
import com.example.api.model.By;
import com.example.api.model.SortBy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

@RequestMapping("/v1/films")
@Tag(name = "Фильмы", description = "Взаимодействие с фильмами")
public interface FilmApi {

    @PostMapping
    @Operation(description = "Добавление нового фильма")
    FilmDto create(@RequestBody @Valid FilmDto filmDto);

    @PutMapping
    @Operation(description = "Обновление информации о фильме")
    FilmDto update(@RequestBody @Valid FilmDto filmDto);

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о фильме")
    FilmDto findById(@PathVariable Long id);

    @GetMapping
    @Operation(description = "Получение списка всех фильмов")
    List<FilmDto> findAll();

    @GetMapping("/director/{directorId}")
    @Operation(description = "Получение списка всех фильмов указанного режиссёра")
    List<FilmDto> findAllByDirectorId(@PathVariable Long directorId,
                                      @Parameter(description = "Критерий сортировки фильмов", schema = @Schema(allowableValues = {"likes", "year"})) @RequestParam SortBy sortBy);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление фильма")
    void deleteById(@PathVariable Long id);

    @PutMapping("/{id}/like/{userId}")
    @Operation(description = "Добавление положительной оценки")
    void addLike(@PathVariable Long id, @PathVariable Long userId);

    @DeleteMapping("/{id}/like/{userId}")
    @Operation(description = "Удаление положительной оценки")
    void deleteLike(@PathVariable Long id, @PathVariable Long userId);

    @GetMapping("/common")
    @Operation(description = "Получение списка всех общих фильмов")
    List<FilmDto> findCommon(@Parameter(description = "Идентификатор пользователя") @RequestParam Long userId,
                             @Parameter(description = "Идентификатор пользователя") @RequestParam Long friendId);

    @GetMapping("/popular")
    @Operation(description = "Получение списка всех популярных фильмов")
    List<FilmDto> findPopular(@Parameter(description = "Максимальное количество элементов") @RequestParam(defaultValue = "10") Integer count,
                              @Parameter(description = "Идентификатор жанра фильма") @RequestParam(required = false) Long genreId,
                              @Parameter(description = "Год выхода фильма") @RequestParam(required = false) Integer year);

    @GetMapping("/search")
    @Operation(description = "Поиск фильмов")
    List<FilmDto> search(@Parameter(description = "Текст для поиска фильмов") @RequestParam String query,
                         @Parameter(description = "Список критериев для поиска фильмов", schema = @Schema(type = "list", allowableValues = {"director", "title"})) @RequestParam List<By> by);
}
