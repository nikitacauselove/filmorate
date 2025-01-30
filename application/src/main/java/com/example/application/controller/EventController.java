package com.example.application.controller;

import com.example.api.EventApi;
import com.example.api.model.EventDto;
import com.example.application.controller.mapper.EventDtoMapper;
import com.example.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventDtoMapper eventDtoMapper;
    private final EventService eventService;

    @Override
    public List<EventDto> findAllByUserId(Long userId) {
        return eventDtoMapper.toDto(eventService.findAllByUserId(userId));
    }
}
