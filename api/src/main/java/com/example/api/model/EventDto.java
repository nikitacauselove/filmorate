package com.example.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
@Schema(description = "Информация о действии пользователя")
public record EventDto(@Schema(description = "Идентификатор действия пользователя") Long eventId,
                       @Schema(description = "Временная метка действия пользователя") Long timestamp,
                       @Schema(description = "Идентификатор пользователя") Long userId,
                       @Schema(description = "Тип действия пользователя") EventType eventType,
                       @Schema(description = "Тип операции") Operation operation,
                       @Schema(description = "Идентификатор сущности") Long entityId) {
}
