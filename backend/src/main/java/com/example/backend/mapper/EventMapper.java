package com.example.backend.mapper;

import com.example.api.dto.EventDto;
import com.example.backend.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventId", source = "id")
    EventDto toEventDto(Event event);

    List<EventDto> toEventDto(List<Event> events);
}
