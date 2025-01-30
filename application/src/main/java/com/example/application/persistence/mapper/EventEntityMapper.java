package com.example.application.persistence.mapper;

import com.example.application.domain.Event;
import com.example.application.persistence.model.EventEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventEntityMapper {

    Event toDomain(EventEntity eventEntity);

    List<Event> toDomain(List<EventEntity> eventEntityList);
}
