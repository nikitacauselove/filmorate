package com.example.filmorate.dto;

import com.example.filmorate.entity.Event;
import lombok.Data;

@Data
public class EventDto {
    private final Integer eventId;
    private final Long timestamp;
    private final Integer userId;
    private final Event.EventType eventType;
    private final Event.Operation operation;
    private final Integer entityId;
}
