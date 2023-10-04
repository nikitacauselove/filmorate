package com.filmorate.filmorate.model.dto;

import com.filmorate.filmorate.model.Event;
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
