package com.example.application.mapper;

import com.example.api.dto.EventDto;
import com.example.application.repository.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventId", source = "id")
    @Mapping(target = "timestamp", qualifiedByName = "localDateTimeToLong")
    EventDto toEventDto(Event event);

    List<EventDto> toEventDto(List<Event> eventList);

    @Named("localDateTimeToLong")
    default Long localDateTimeToLong(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }
}
