package com.example.filmorate.controller;

import com.example.filmorate.dto.EventDto;
import com.example.filmorate.mapper.EventMapper;
import com.example.filmorate.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @GetMapping("users/{id}/feed")
    public List<EventDto> findAllByUserId(@PathVariable int id) {
        return eventMapper.toEventDto(eventService.findAllByUserId(id));
    }
}
