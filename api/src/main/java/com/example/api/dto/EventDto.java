package com.example.api.dto;

import lombok.Data;

@Data
public class EventDto {
    private final Integer eventId;
    private final Long timestamp;
    private final Integer userId;
    private final EventType eventType;
    private final EventOperation operation;
    private final Integer entityId;
}
