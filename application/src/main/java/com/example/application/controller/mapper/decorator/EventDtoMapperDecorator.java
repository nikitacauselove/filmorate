package com.example.application.controller.mapper.decorator;

import com.example.api.model.EventDto;
import com.example.application.controller.mapper.EventDtoMapper;
import com.example.application.domain.Event;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

@Setter(onMethod_ = @Autowired)
public abstract class EventDtoMapperDecorator implements EventDtoMapper {

    private EventDtoMapper delegate;

    @Override
    public EventDto toDto(Event event) {
        EventDto eventDto = delegate.toDto(event);
        EventDto.EventDtoBuilder eventDtoBuilder = eventDto.toBuilder();

        eventDtoBuilder.timestamp(Timestamp.valueOf(event.timestamp()).getTime());
        return eventDtoBuilder.build();
    }

    @Override
    public List<EventDto> toDto(List<Event> eventList) {
        return eventList.stream()
                .map(this::toDto)
                .toList();
    }
}
