package com.example.application.controller.mapper;

import com.example.api.model.EventDto;
import com.example.application.domain.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {

    @Mapping(target = "eventId", source = "id")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "toTimestamp")
    EventDto toDto(Event event);

    List<EventDto> toDto(List<Event> eventList);

    @Named("toTimestamp")
    default Long toTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }
}
