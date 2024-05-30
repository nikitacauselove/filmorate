package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Event {
    private Long id;
    private final Long timestamp;
    private final Long userId;
    private final EventType eventType;
    private final Operation operation;
    private final Long entityId;

    public enum EventType {
        FRIEND,
        LIKE,
        REVIEW
    }

    public enum Operation {
        ADD,
        REMOVE,
        UPDATE
    }
}
