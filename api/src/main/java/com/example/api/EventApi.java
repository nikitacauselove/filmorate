package com.example.api;

import com.example.api.dto.EventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Действия пользователей", description = "Взаимодействие с действиями пользователей")
public interface EventApi {

    @GetMapping("/api/users/{id}/feed")
    @Operation(description = "Получение списка всех действий пользователя")
    List<EventDto> findAllByUserId(
            @PathVariable Integer id
    );
}
