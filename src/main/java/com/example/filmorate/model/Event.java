package com.example.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Event {
    private Integer id;
    private final Long timestamp;
    private final Integer userId;
    private final EventType eventType;
    private final Operation operation;
    private final Integer entityId;

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
