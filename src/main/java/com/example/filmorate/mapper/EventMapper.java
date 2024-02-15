package com.example.filmorate.mapper;

import com.example.filmorate.dto.EventDto;
import com.example.filmorate.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventId", source = "id")
    EventDto toEventDto(Event event);

    List<EventDto> toEventDto(List<Event> events);
}
