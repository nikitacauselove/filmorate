package com.example.application.persistence.mapper;

import com.example.application.domain.Event;
import com.example.application.persistence.model.EventEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserEntityMapper.class)
public interface EventEntityMapper {

    EventEntity toEntity(Event event);

    Event toDomain(EventEntity eventEntity);

    List<Event> toDomain(List<EventEntity> eventEntityList);
}
