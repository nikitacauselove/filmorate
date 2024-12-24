package com.example.application.controller;

import com.example.api.EventApi;
import com.example.api.dto.EventDto;
import com.example.application.mapper.EventMapper;
import com.example.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @Override
    public List<EventDto> findAllByUserId(Long userId) {
        return eventMapper.toEventDto(eventService.findAllByUserId(userId));
    }
}
