package com.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о действии пользователя")
public record EventDto(@Schema(description = "Идентификатор действия") Integer eventId,
                       @Schema(description = "Время") Long timestamp,
                       @Schema(description = "Идентификатор пользователя") Integer userId,
                       @Schema(description = "Тип действия") EventType eventType,
                       @Schema(description = "Тип операции") EventOperation operation,
                       @Schema(description = "Идентификатор сущности") Integer entityId) {
}
