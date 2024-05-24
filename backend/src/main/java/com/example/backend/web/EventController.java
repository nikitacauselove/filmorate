package com.example.backend.web;

import com.example.api.EventApi;
import com.example.api.dto.EventDto;
import com.example.backend.mapper.EventMapper;
import com.example.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventMapper eventMapper;
    private final EventService eventService;

    public List<EventDto> findAllByUserId(@PathVariable Integer id) {
        return eventMapper.mapToEventDto(eventService.findAllByUserId(id));
    }
}
