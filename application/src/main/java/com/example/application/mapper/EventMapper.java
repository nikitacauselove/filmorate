package com.example.application.mapper;

import com.example.api.dto.EventDto;
import com.example.application.mapper.decorator.EventMapperDecorator;
import com.example.application.repository.entity.Event;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@DecoratedWith(EventMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "eventType", ignore = true)
    @Mapping(target = "operation", ignore = true)
    @Mapping(target = "entityId", ignore = true)
    EventDto toEventDto(Event event);

    List<EventDto> toEventDto(List<Event> eventList);
}
