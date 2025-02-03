package com.example.application.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record Event(Long id, LocalDateTime timestamp, User user, EventType eventType, Operation operation,
                    Long entityId) {
}
