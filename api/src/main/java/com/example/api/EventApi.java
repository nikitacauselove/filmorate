package com.example.api;

import com.example.api.model.EventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Действия пользователей", description = "Взаимодействие с действиями пользователей")
public interface EventApi {

    @GetMapping("/v1/users/{userId}/feed")
    @Operation(description = "Получение списка всех действий пользователя")
    List<EventDto> findAllByUserId(@PathVariable Long userId);
}
