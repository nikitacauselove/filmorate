package com.example.application.mapper.decorator;

import com.example.api.dto.EventDto;
import com.example.api.dto.enums.EventType;
import com.example.api.dto.enums.Operation;
import com.example.application.mapper.EventMapper;
import com.example.application.repository.entity.Event;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

@Setter(onMethod_ = @Autowired)
public abstract class EventMapperDecorator implements EventMapper {

    private EventMapper delegate;

    @Override
    public EventDto toEventDto(Event event) {
        Long eventId = event.getId();
        Long timestamp = Timestamp.valueOf(event.getTimestamp()).getTime();
        Long userId = event.getUserId();
        EventType eventType = event.getEventType();
        Operation operation = event.getOperation();
        Long entityId = event.getEntityId();

        return new EventDto(eventId, timestamp, userId, eventType, operation, entityId);
    }

    @Override
    public List<EventDto> toEventDto(List<Event> events) {
        return events.stream()
                .map(this::toEventDto)
                .toList();
    }
}
