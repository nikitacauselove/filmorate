package com.example.api;

import com.example.api.dto.ReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RequestMapping("/v1/reviews")
@Tag(name = "Рецензии", description = "Взаимодействие с рецензиями на фильмы")
public interface ReviewApi {

    @PostMapping
    @Operation(description = "Добавление новой рецензии")
    ReviewDto create(@RequestBody @Valid ReviewDto reviewDto);

    @PutMapping
    @Operation(description = "Обновление рецензии")
    ReviewDto update(@RequestBody @Valid ReviewDto reviewDto);

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о рецензии")
    ReviewDto findById(@PathVariable Long id);

    @GetMapping
    @Operation(description = "Получение списка всех рецензий")
    List<ReviewDto> findAll(@Parameter(description = "Идентификатор фильма") @RequestParam(required = false) Long filmId,
                            @Parameter(description = "Максимальное количество элементов") @RequestParam(defaultValue = "10") Integer count);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление рецензии")
    void deleteById(@PathVariable Long id);

    @PutMapping("/{id}/like/{userId}")
    @Operation(description = "Добавление положительной реакции")
    void addLike(@PathVariable Long id, @PathVariable Long userId);

    @DeleteMapping("/{id}/like/{userId}")
    @Operation(description = "Удаление положительной реакции")
    void deleteLike(@PathVariable Long id, @PathVariable Long userId);

    @PutMapping("/{id}/dislike/{userId}")
    @Operation(description = "Добавление отрицательной реакции")
    void addDislike(@PathVariable Long id, @PathVariable Long userId);

    @DeleteMapping("/{id}/dislike/{userId}")
    @Operation(description = "Удаление отрицательной реакции")
    void deleteDislike(@PathVariable Long id, @PathVariable Long userId);
}
