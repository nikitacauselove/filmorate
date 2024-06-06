package com.example.backend.mapper;

import com.example.api.dto.EventDto;
import com.example.backend.repository.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventId", source = "id")
    EventDto mapToEventDto(Event event);

    List<EventDto> mapToEventDto(List<Event> eventList);
}
