package com.example.backend.mapper;

import com.example.api.dto.EventDto;
import com.example.backend.repository.entity.Event;
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
    EventDto mapToEventDto(Event event);

    List<EventDto> mapToEventDto(List<Event> eventList);

    @Named("localDateTimeToLong")
    default Long localDateTimeToLong(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }
}
