package com.example.api;

import com.example.api.dto.FilmDto;
import com.example.api.dto.UserDto;
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

@RequestMapping("/v1/users")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
public interface UserApi {

    @PostMapping
    @Operation(description = "Добавление нового пользователя")
    UserDto create(@RequestBody @Valid UserDto userDto);

    @PutMapping
    @Operation(description = "Обновление информации о пользователе")
    UserDto update(@RequestBody @Valid UserDto userDto);

    @GetMapping("/{id}")
    @Operation(description = "Получение пользователя")
    UserDto findById(@PathVariable Long id);

    @GetMapping
    @Operation(description = "Получение списка всех пользователей")
    List<UserDto> findAll();

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление пользователя")
    void deleteById(@PathVariable Long id);

    @PutMapping("/{id}/friends/{friendId}")
    @Operation(description = "Добавление пользователя в список друзей")
    void addFriend(@PathVariable Long id, @PathVariable Long friendId);

    @DeleteMapping("/{id}/friends/{friendId}")
    @Operation(description = "Удаление пользователя из списка друзей")
    void deleteFriend(@PathVariable Long id, @PathVariable Long friendId);

    @GetMapping("/{id}/friends")
    @Operation(description = "Получение списка всех друзей пользователя")
    List<UserDto> findAllFriends(@PathVariable Long id);

    @GetMapping("/{id}/friends/common/{otherUserId}")
    @Operation(description = "Получение списка всех общих друзей")
    List<UserDto> findCommonFriends(@PathVariable Long id, @PathVariable Long otherUserId);

    @GetMapping("/{id}/recommendations")
    @Operation(description = "Получение списка фильмов, рекомендованных для просмотра")
    List<FilmDto> findRecommendations(@PathVariable Long id);
}
