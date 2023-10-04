package com.filmorate.filmorate.controller;

import com.filmorate.filmorate.model.dto.EventDto;
import com.filmorate.filmorate.model.mapper.EventMapper;
import com.filmorate.filmorate.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventController {
    private final EventService eventService;

    @GetMapping("users/{id}/feed")
    public List<EventDto> findAllByUserId(@PathVariable int id) {
        return EventMapper.toEventDto(eventService.findAllByUserId(id));
    }
}
