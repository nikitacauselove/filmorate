package com.example.application.domain;

import com.example.api.model.type.EventType;
import com.example.api.model.type.Operation;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record Event(Long id, LocalDateTime timestamp, Long userId, EventType eventType, Operation operation, Long entityId) {
}
