package com.example.application.controller;

import com.example.api.GenreApi;
import com.example.api.model.GenreDto;
import com.example.application.controller.mapper.GenreDtoMapper;
import com.example.application.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController implements GenreApi {

    private final GenreDtoMapper genreDtoMapper;
    private final GenreService genreService;

    @Override
    public GenreDto findById(Long id) {
        return genreDtoMapper.toDto(genreService.findById(id));
    }

    @Override
    public List<GenreDto> findAll() {
        return genreDtoMapper.toDto(genreService.findAll());
    }
}
