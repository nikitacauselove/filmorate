package com.example.application.controller.mapper;

import com.example.api.model.EventDto;
import com.example.application.controller.mapper.decorator.EventDtoMapperDecorator;
import com.example.application.domain.Event;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@DecoratedWith(EventDtoMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface EventDtoMapper {

    @Mapping(target = "eventId", source = "id")
    @Mapping(target = "timestamp", ignore = true)
    EventDto toDto(Event event);

    List<EventDto> toDto(List<Event> eventList);
}
