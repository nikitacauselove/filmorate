package com.example.api.dto;

import com.example.api.dto.enums.EventOperation;
import com.example.api.dto.enums.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Schema(description = "Информация о действии пользователя")
public record EventDto(@Schema(description = "Идентификатор действия") Long eventId,
                       @Schema(description = "Время") Long timestamp,
                       @Schema(description = "Идентификатор пользователя") Long userId,
                       @Schema(description = "Тип действия") EventType eventType,
                       @Schema(description = "Тип операции") EventOperation operation,
                       @Schema(description = "Идентификатор сущности") Long entityId) {
}
